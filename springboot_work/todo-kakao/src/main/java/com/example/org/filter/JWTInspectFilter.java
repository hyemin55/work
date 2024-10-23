package com.example.org.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JWTInspectFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
//        log.info("Filter 지나간당");

         /*
        프론트 에서 넘겨준
        jwt 토큰 까서...
        UserPasswordAuthenticationToken
        authentication
        securityContext
         */
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        // TODO: JWT validation and authentication
        filterChain.doFilter(request, response);
    }
}
