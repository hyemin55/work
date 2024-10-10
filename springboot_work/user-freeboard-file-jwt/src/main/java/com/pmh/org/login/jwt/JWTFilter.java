package com.pmh.org.login.jwt;

import com.pmh.org.login.LoginUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTManager jwtManager;

    public JWTFilter(JWTManager jwtManager) {
        this.jwtManager = jwtManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("여기를 무조건 지나간다.");

        String auth = request.getHeader(HttpHeaders.AUTHORIZATION);

//        인증 토큰인 JWT가 null이거나 Bearer로 시작하는 토근이 아니면
        if (auth == null || !auth.startsWith("Bearer")) {
//            그냥 지나간다.
            filterChain.doFilter(request, response);
            return;
        }
//        회원가입이나 로그인으로 들어왔을 때는 로그인 설정하지 말고 지나가기.
//            if(request.getRequestURI().contains("join") || request.getRequestURI().contains("login")){
//                filterChain.doFilter(request, response);
//                return;
//        }



//        System.out.println("auth = "+auth);

        // JWT 토큰이 유효한지 확인 해보는 함수
//        jwtManager.validJWT(auth);

//        값이 Bearer fjdsfja;ldsjfsjdflkjs;fkjskl 일 때
//        String  token = auth.substring(7);
        try {
            String token = auth.split(" ")[1];

            Jws<Claims> claims = jwtManager.getClaims(token);
            String email = claims.getPayload().get("email").toString();
//        String role = "ADMIN";
            String role = claims.getPayload().get("role").toString();

            LoginUserDetails loginUserDetails = new LoginUserDetails(email, null, role);

//        UserDetails userDetails = org.springframework.security.core.userdetails
//                .User
//                .builder()
//                .username(email)
//                .roles(role)
//                .build();

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    loginUserDetails, null, loginUserDetails.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }catch (Exception e){
            e.printStackTrace();
        }
        // 여기서 무조건 지나가는
        filterChain.doFilter(request, response);
    }
}