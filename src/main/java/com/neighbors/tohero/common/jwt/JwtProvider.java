package com.neighbors.tohero.common.jwt;

import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.common.enums.Role;
import com.neighbors.tohero.common.exception.jwt.bad_request.JwtUnsupportedTokenException;
import com.neighbors.tohero.common.exception.jwt.unauthorized.JwtInvalidTokenException;
import com.neighbors.tohero.common.exception.jwt.unauthorized.JwtMalformedTokenException;
import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtProvider {

    @Value("${secret.jwt-secret-key}")
    private String JWT_SECRET_KEY;

    @Value("${secret.jwt-expired-in.access-token}")
    private long JWT_EXPIRED_IN;

    @Value("${secret.jwt-expired-in.refresh-token}")
    @Getter
    private long REFRESH_TOKEN_EXPIRED_IN;

    public AuthTokens createToken(JwtUserDetails jwtUserDetails) {
        log.info("JWT key={}", JWT_SECRET_KEY);

        Claims claims = Jwts.claims()
                .setSubject(jwtUserDetails.getEmail())
                .setIssuer("zipkok");

        claims.put("role", jwtUserDetails.getRole());
        claims.put("id", jwtUserDetails.getUserId());

        Date now = new Date();
        Date accessTokenExpiredAt = new Date(now.getTime() + JWT_EXPIRED_IN);
        Date refreshTokenExpiredAt = new Date(now.getTime() + REFRESH_TOKEN_EXPIRED_IN);

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(accessTokenExpiredAt)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
                .compact();

        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(refreshTokenExpiredAt)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
                .compact();

        return AuthTokens.of(accessToken, refreshToken, JWT_EXPIRED_IN, REFRESH_TOKEN_EXPIRED_IN);
    }

    public boolean isExpiredToken(String token) throws JwtInvalidTokenException {
        log.info("[JwtTokenProvider.isExpiredToken] token={}", token);
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(JWT_SECRET_KEY).build()
                    .parseClaimsJws(token);

            return claims.getBody().getExpiration().before(new Date());

        } catch (ExpiredJwtException e) {
            log.error("[ExpiredJwtException]");
            return true;
        } catch (UnsupportedJwtException e) {
            log.error("[UnsupportedJwtException]");
            throw new JwtUnsupportedTokenException(BaseResponseStatus.JWT_ERROR, BaseResponseMessage.지원하지_않는_토큰_입니다.getMessage());
        } catch (MalformedJwtException e) {
            log.error("[MalformedJwtException]");
            throw new JwtMalformedTokenException(BaseResponseStatus.JWT_ERROR, BaseResponseMessage.토큰이_올바르지_못한_형식입니다.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("[IllegalArgumentException]");
            throw new JwtInvalidTokenException(BaseResponseStatus.JWT_ERROR, BaseResponseMessage.유효하지_않은_토큰_입니다.getMessage());
        } catch (JwtException e) {
            log.error("[JwtTokenProvider.validateAccessToken]", e);
            throw e;
        }
    }

    public String getEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET_KEY).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Long getId(String token) {
        return Long.valueOf(Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET_KEY).build()
                .parseClaimsJws(token)
                .getBody()
                .getId());
    }

    private Claims getBody(String token) {
        if (token.contains("Bearer ")) {
            token = token.substring("Bearer ".length());
        }

        return Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public JwtUserDetails getJwtUserDetails(String token) {
        Claims claims = getBody(token);

        return JwtUserDetails.builder()
                .email(String.valueOf(claims.getSubject()))
                .userId(Long.valueOf(claims.get("id").toString()))
                .role(Role.valueOf(claims.get("role").toString()))
                .build();

    }
}