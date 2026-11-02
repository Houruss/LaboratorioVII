package com.umg.laboratoriovii.proveedores.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {
    private final String apiKey;

    public ApiKeyFilter(@Value("${app.api-key}") String apiKey) {
        if (apiKey.isBlank()) {
            throw new IllegalArgumentException("La variable API_KEY no puede estar vacia");
        }
        this.apiKey = apiKey;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getRequestURI().startsWith("/api/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException {
        String recibida = request.getHeader("X-API-KEY");
        boolean valida = recibida != null && MessageDigest.isEqual(
                apiKey.getBytes(StandardCharsets.UTF_8),
                recibida.getBytes(StandardCharsets.UTF_8));
        if (!valida) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setContentType("application/json");
            response.getWriter().write(
                    "{\"status\":401,\"mensaje\":\"API Key invalida o ausente\"}");
            return;
        }
        chain.doFilter(request, response);
    }
}
