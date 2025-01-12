package com.neighbors.tohero.common.security;

import com.neighbors.tohero.common.enums.Role;
import com.neighbors.tohero.common.jwt.JwtProvider;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationUtil {

    private final JwtProvider jwtProvider;
    private Map<String, List<String>> onlyUserRequest;

    @PostConstruct
    private void initOnlyUserRequest() {
        onlyUserRequest = new HashMap<>();

        // 초기화
        addToOnlyUserRequest("PUT", "/user/name");
        addToOnlyUserRequest("POST", "/user/signout");
        addToOnlyUserRequest("POST", "/user/logout");
        addToOnlyUserRequest("GET", "/letter");
        addToOnlyUserRequest("PUT", "/letter");
        addToOnlyUserRequest("GET", "/auth/refreshToken");
    }

    private void addToOnlyUserRequest(String method, String url) {
        onlyUserRequest.computeIfAbsent(method, k -> new ArrayList<>()).add(url);
    }

    public void setAuthenticationFromRequest(HttpServletRequest request) {

        log.info("[AuthenticationUtil.setAuthenticationFromRequest]");

        final String token = getJwtFromRequest(request);

        makeAuthentication(request, token).ifPresent(auth -> {
            SecurityContextHolder.getContext().setAuthentication(auth);
        });
    }

    private Boolean isRequestAvailableToGuest(String token) {
        return jwtProvider.getGuestJwtUserDetails(token).getRole() == Role.GUEST;
    }

    private Optional<UserAuthentication> makeAuthentication(HttpServletRequest request, String token) {

        log.info("[AuthenticationUtil.makeAuthentication]");

        UserAuthentication authentication = null;

        if(isTokenValid(token)) {
            if (isRequestAvailableToGuest(token)) {
                if(checkGuestAccessRequest(request)){
                    log.info("[AuthenticationUtil.makeAuthentication : Guest 권한 부여]");
                    String nickname = jwtProvider.getGuestJwtUserDetails(token).getNickname();
                    authentication = UserAuthentication.makeGuestAuthentication(nickname);
                }
            }
            else {
                log.info("[AuthenticationUtil.makeAuthentication : User 권한 부여]");
                authentication = UserAuthentication.from(jwtProvider.getJwtUserDetails(token));
            }
        }

        if(authentication != null) {
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        }

        return Optional.ofNullable(authentication);
    }

    private boolean checkGuestAccessRequest(HttpServletRequest request) {
        List<String> urls = onlyUserRequest.get(request.getMethod());
        if (urls != null) {
            for (String url : urls) {
                if (request.getRequestURI().contains(url)) {
                    return false;
                }
            }
        }
        return true;
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        } else {
            return null;
        }

    }

    private boolean isTokenValid(String token) {
        return token != null && !jwtProvider.isExpiredToken(token);
    }

}
