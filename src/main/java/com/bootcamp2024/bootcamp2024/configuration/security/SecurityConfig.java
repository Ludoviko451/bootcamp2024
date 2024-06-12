package com.bootcamp2024.bootcamp2024.configuration.security;

import com.bootcamp2024.bootcamp2024.configuration.security.jwt.JwtAuthenticationEntryPoint;
import com.bootcamp2024.bootcamp2024.configuration.security.jwt.JwtAuthenticationFilter;
import com.bootcamp2024.bootcamp2024.configuration.security.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {




    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtTokenProvider tokenProvider;

    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtTokenProvider tokenProvider) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.tokenProvider = tokenProvider;
    }


    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(tokenProvider);

    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling .authenticationEntryPoint(jwtAuthenticationEntryPoint))

                .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/technology/").hasAuthority(SecurityContants.ADMIN)
                        .requestMatchers(HttpMethod.POST, "/capacity/").hasAuthority(SecurityContants.ADMIN)
                        .requestMatchers(HttpMethod.POST, "/bootcamp/").hasAuthority(SecurityContants.ADMIN)
                        .requestMatchers(HttpMethod.POST, "/version/").hasAuthority(SecurityContants.ADMIN)
                        .anyRequest().authenticated()
                )

                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
