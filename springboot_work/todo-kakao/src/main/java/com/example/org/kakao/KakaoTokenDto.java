package com.example.org.kakao;

import lombok.Data;

@Data
public class KakaoTokenDto {

        private String access_token;
        private String refresh_token;
        private String token_type;
        private String expires_in;
        private String scope;
        private String refresh_token_expires_in;
}
