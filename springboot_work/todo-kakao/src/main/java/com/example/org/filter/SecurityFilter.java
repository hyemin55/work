package com.example.org.filter;

import com.example.org.error.UserException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//@Component

@Slf4j
public class SecurityFilter extends OncePerRequestFilter {

    private final JWTUtils jwtUtils;

    public SecurityFilter(JWTUtils jwtUtils) {}
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("Security Filter");

        String authorization = request.getHeader("Authorization");
        log.info("Security Filter" + authorization);

//       securityContextHolder 만들지 않고(로그인 하지않으면) 그냥 지나가게 만듬.
        if(authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
//       securityContextHolder 만들고(로그인 하면).
        String jwt = authorization.split("Bearer ")[1];
        String email = jwtUtils.getEmailFromJwt(jwt);
        if (email != null) {
            throw new UserException("user email not found");
        }



        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        User.builder()
                                .username(email)
                                .password("temp")
                                .roles("ADMIN")
                                .build()
                        , null);


        /*
        프론트 에서 넘겨준
        jwt 토큰 까서...
        UserPasswordAuthenticationToken
        authentication
        securityContext
         */

//        SecurityContextHolder.getContext()
//                .setAuthentication(authentication);

        // 지나가라...
        filterChain.doFilter(request, response);
    }
}