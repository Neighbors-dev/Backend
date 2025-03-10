package com.neighbors.tohero.common.config;

import com.neighbors.tohero.common.security.CustomAccessDeniedHandler;
import com.neighbors.tohero.common.security.CustomJwtAuthenticationEntryPoint;
import com.neighbors.tohero.common.security.JwtAuthenticationFilter;
import jakarta.annotation.Priority;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@Priority(0)
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomJwtAuthenticationEntryPoint customJwtAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                "/error",
                "/swagger-ui/**",
                "/swagger-resources/*",
                "/webjars/",
                "/v3/api-docs/**",
                "/api/oauth/kakao/callback",
                "/api/oauth/kakao/callback2",
                "/api/user/auth",
                "/api/auth/refreshToken",
                "/api/address",
                "/api/notice/**",
                "/api/mainPage/**",
                "/api/letter/detail",
                "/api/news"
        );
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) //csrf 토큰 disable 하기
                .formLogin(AbstractHttpConfigurer::disable) //form login 비활성화
                .httpBasic(AbstractHttpConfigurer::disable)//http 기본 인증 비활성화
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception ->
                {
                    exception.authenticationEntryPoint(customJwtAuthenticationEntryPoint);
                    exception.accessDeniedHandler(customAccessDeniedHandler);
                })
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 쿠키 포함 허용
        // 여러 도메인 허용
        List<String> allowedOrigins = new ArrayList<>();
        allowedOrigins.add("https://glittery-madeleine-215e2f.netlify.app");
        allowedOrigins.add("https://tohero.co.kr");
        allowedOrigins.add("http://localhost:5173");
        config.setAllowedOrigins(allowedOrigins); // 여러 도메인 추가
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // 허용할 HTTP 메서드
        config.setAllowedHeaders(List.of("*")); // 모든 헤더 허용
        config.setExposedHeaders(List.of("Authorization")); // 노출할 헤더

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 모든 URL에 대해 설정 적용

        return new CorsFilter(source);
    }
}
