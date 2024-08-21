package com.chargebee.ingestion.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final ApiKeyFilter apiKeyFilter;

    @Autowired
    public SecurityConfig(ApiKeyFilter apiKeyFilter) {
        this.apiKeyFilter = apiKeyFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/v1/usage").authenticated()
            .antMatchers("/api/kafka/create-topic").permitAll()
            .antMatchers("/actuator/health", "/actuator/info").permitAll()
            .anyRequest().denyAll()
            .and()
            .addFilterBefore(apiKeyFilter, UsernamePasswordAuthenticationFilter.class)
            .csrf().disable();

        return http.build();
    }
}
