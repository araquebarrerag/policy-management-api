package com.example.policy_management_api.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    private static final String API_KEY = "123456";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException
    {
        String apiKey = request.getHeader("x-api-key");
        if (!API_KEY.equals(apiKey)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            response.getWriter()
                    .write("API KEY Inválida");

            return;
        }

        filterChain.doFilter(request, response);
    }
}
