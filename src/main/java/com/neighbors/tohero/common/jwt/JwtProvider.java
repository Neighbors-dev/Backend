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
        Claims claims = Jwts.claims()
                .setSubject(jwtUserDetails.getNickname())
                .setIssuer("ToHero");

        claims.put("role", jwtUserDetails.getRole());
        if(jwtUserDetails.getRole() == Role.USER) {
            claims.put("userId", jwtUserDetails.getUserId());
            claims.put("email", jwtUserDetails.getEmail());
        }

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

    public String createRecommenderCode(String userEmail, String recommenderEmails){
        Claims claims = Jwts.claims()
                .setSubject(userEmail)
                .setIssuer("ToHero");

        Date now = new Date();
        claims.put("recommenderEmails", recommenderEmails);
        claims.put("issueTime", now);

        Date accessTokenExpiredAt = new Date(now.getTime() + JWT_EXPIRED_IN);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(accessTokenExpiredAt)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
                .compact();
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

    public String getNickname(String token) {
        Claims claims = getBody(token);

        return String.valueOf(claims.getSubject());
    }

    public boolean isGuestToken(String token) {
        Claims claims = getBody(token);

        return Role.valueOf(claims.get("role").toString()) == Role.GUEST;
    }

    public void loggingToken(String token) {
        Claims tokenInfo = Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET_KEY).build()
                .parseClaimsJws(token)
                .getBody();
        log.info("[JwtTokenProvider.loggingToken] token={}", tokenInfo);
    }

    public Long getId(String token) {
        Claims claims = getBody(token);
        return Long.parseLong(claims.get("userId").toString());
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
                .nickname(String.valueOf(claims.getSubject()))
                .role(Role.valueOf(claims.get("role").toString()))
                .email(claims.get("email").toString())
                .userId(Long.parseLong(claims.get("userId").toString()))
                .build();

    }

    public String getRecommenderEmails(String token) {
        Claims claims = getBody(token);
        return claims.get("recommenderEmails").toString();
    }

    public JwtUserDetails getGuestJwtUserDetails(String token) {
        Claims claims = getBody(token);

        return JwtUserDetails.builder()
                .nickname(String.valueOf(claims.getSubject()))
                .role(Role.valueOf(claims.get("role").toString()))
                .build();

    }
}