package com.chargebee.ingestion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String VALID_API_KEY = "KB73Xz7kH7F14vcSZLSoEXcJaL1VnC3xWR67MOtBBMxkkBnkkYoxDSqcKtYTzxB33zHE0p5IZsSsVFJczYxUt0atgTvhAYVyMPZd34JCgeUDj1gQywtxMkC1QXabLGKl";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/actuator/health", "/actuator/info").permitAll() // Public endpoints
                .anyRequest().authenticated() // All other requests require authentication
                .and()
                .addFilterBefore(new ApiKeyFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();

        return http.build();
    }

    private class ApiKeyFilter extends OncePerRequestFilter {

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {
            String apiKey = request.getHeader("X-API-Key");
            if (isPublicEndpoint(request.getRequestURI()) || isValidApiKey(apiKey)) {
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken("user", null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
                );
            } else {
                SecurityContextHolder.clearContext();
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid API Key");
                return;
            }

            filterChain.doFilter(request, response);
        }

        private boolean isValidApiKey(String apiKey) {
            return VALID_API_KEY.equals(apiKey);
        }

        private boolean isPublicEndpoint(String uri) {
            // Return true if the URI matches public endpoints
            return "/actuator/health".equals(uri) || "/actuator/info".equals(uri);
        }
    }
}
