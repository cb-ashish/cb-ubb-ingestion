package com.chargebee.ingestion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/v1/usage").authenticated() // Secure the usage API endpoint
                .antMatchers("/api/kafka/create-topic").authenticated() // Secure the Kafka API endpoint
                .antMatchers("/actuator/health").permitAll()  // Allow access to health endpoint
                .antMatchers("/actuator/info").permitAll()   // Allow access to info endpoint
                .anyRequest().denyAll()
                .and()
                .addFilterBefore(new ApiKeyFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable(); // Disable CSRF if not using stateful sessions
        return http.build();
    }

    public class ApiKeyFilter extends OncePerRequestFilter {
        private static final String API_KEY_HEADER = "X-API-Key";
        private static final String VALID_API_KEY = "KB73Xz7kH7F14vcSZLSoEXcJaL1VnC3xWR67MOtBBMxkkBnkkYoxDSqcKtYTzxB33zHE0p5IZsSsVFJczYxUt0atgTvhAYVyMPZd34JCgeUDj1gQywtxMkC1QXabLGKl";

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {
            String apiKey = request.getHeader(API_KEY_HEADER);

            if (VALID_API_KEY.equals(apiKey)
                    || request.getRequestURI().equals("/actuator/health")
                    ||  request.getRequestURI().equals("/actuator/info")) {
                // Set authentication context
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken("user", null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
                );
            } else {
                // Authentication failed
                SecurityContextHolder.clearContext();
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid API Key");
                return;
            }

            filterChain.doFilter(request, response);
        }
    }

}
