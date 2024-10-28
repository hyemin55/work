package com.example.org.user;

import com.example.org.kakao.jpa.KakaoEntity;
import com.example.org.kakao.jpa.KakaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final KakaoRepository kakaoRepository;

    @GetMapping("info")
    public ResponseEntity<KakaoEntity> getUserInfo(
//            @RequestHeader("Authorization") String token,
            @AuthenticationPrincipal UserDetails userDetails){
        if(userDetails == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        KakaoEntity kakaoEntity = kakaoRepository.findByEmail(userDetails.getUsername());

        return ResponseEntity.ok(kakaoEntity);
    }

}