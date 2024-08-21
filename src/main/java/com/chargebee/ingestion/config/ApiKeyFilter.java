package com.chargebee.ingestion.config;

import com.chargebee.ingestion.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    private final LoggingService loggingService;

    @Autowired
    public ApiKeyFilter(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String apiKey = request.getHeader("X-API-Key");
        loggingService.log(String.format("Request URI %s API Key %s", request.getRequestURI(), apiKey));

        if (isValidApiKey(apiKey) || isPublicEndpoint(request.getRequestURI())) {
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
        return "KB73Xz7kH7F14vcSZLSoEXcJaL1VnC3xWR67MOtBBMxkkBnkkYoxDSqcKtYTzxB33zHE0p5IZsSsVFJczYxUt0atgTvhAYVyMPZd34JCgeUDj1gQywtxMkC1QXabLGKl".equals(apiKey);
    }

    private boolean isPublicEndpoint(String uri) {
        return "/actuator/health".equals(uri) || "/actuator/info".equals(uri) || "/api/kafka/create-topic".equals(uri);
    }
}
