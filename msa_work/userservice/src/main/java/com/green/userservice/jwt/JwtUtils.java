package com.green.userservice.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtUtils {
    private String SECRET_KEY = "abcdefghijklmnopqrstuvwxyz01234567890";
    private SecretKey secretKey = new SecretKeySpec(
            Base64.getEncoder().encode( SECRET_KEY.getBytes()),
            SignatureAlgorithm.HS256.getJcaName());
    public String createJwt(String email){
        String jwt = Jwts.builder()
                .claim("email",email)
                .claim("role","USER")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1초*60*60*24 1일 유효함
                .signWith(secretKey)
                .compact();
        return jwt;
    }
}
