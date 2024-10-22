package com.example.org.kakao;

import com.example.org.kakao.dto.KakaoTokenDto;
import com.example.org.kakao.dto.KakaoUserInfoDto;
import com.example.org.kakao.jpa.KakaoEntity;
import com.example.org.kakao.jpa.KakaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoService {

    private final KakaoRepository kakaoRepository;

    public void getToken(String  code){
        try{
            // token 확인 주소..
            String url = "https://kauth.kakao.com/oauth/token";
            RestTemplate restTemplate = new RestTemplate();

            MultiValueMap headers = new LinkedMultiValueMap();
            headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("grant_type", "authorization_code");
            body.add("client_id", "3729987ab56f48c56116ec21b049a78e");
            body.add("redirect_uri", "http://localhost:5173/oauth");
            body.add("code", code);
            body.add("client_secret", "rvxajLH92o1Lz9Joj69mju2A0pD5lVgi");

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
            ResponseEntity<KakaoTokenDto> result = restTemplate.exchange(url,
                    HttpMethod.POST,
                    requestEntity,
                    KakaoTokenDto.class);
            log.info("result {}", result);

            KakaoTokenDto kakaoTokenDto = result.getBody();

//            유저정보 가져오기 시작...
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + kakaoTokenDto.getAccess_token());

            ResponseEntity<KakaoUserInfoDto> res = restTemplate.exchange("https://kapi.kakao.com/v2/user/me",
                    HttpMethod.GET,
                    new HttpEntity<>(null, httpHeaders),
                    KakaoUserInfoDto.class
            );
            System.out.println("KakaoUserInfoDto = "+res.getBody());

//          유저정보 가져오기 끝.
            KakaoEntity kakaoEntity = new ModelMapper().map(kakaoTokenDto, KakaoEntity.class);

            KakaoUserInfoDto kakaoUserInfoDto = res.getBody();
            kakaoEntity.setEmail(kakaoUserInfoDto.getKakaoAccount().getEmail());
            kakaoEntity.setNickname(kakaoUserInfoDto.getKakaoAccount().getProfile().getNickname());
            kakaoEntity.setProfile_image(kakaoUserInfoDto.getProperties().getProfileImage());
            kakaoEntity.setThumbnail_image(kakaoUserInfoDto.getProperties().getThumbnailImage());

            // userId 중복 안되게 생성...
            kakaoEntity.setUserId(UUID.randomUUID().toString());

//            db 저장하기
            kakaoRepository.save(kakaoEntity);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void messageSend(String email, String message){

        RestTemplate restTemplate = new RestTemplate();
        // 메시지 보내는 주소...
        String url = "https://kapi.kakao.com/v2/api/talk/memo/default/send";


        MultiValueMap headers2 = new LinkedMultiValueMap();
        headers2.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//        headers2.add("Authorization", "Bearer " + kakaoTokenDto.getAccess_token());

        MultiValueMap<String, String> body2 = new LinkedMultiValueMap<>();
        body2.add("template_object", String.format(messageString(),"aaa@naver.com"));

        HttpEntity<MultiValueMap<String, String>> requestEntity2 = new HttpEntity<>(body2, headers2);

        ResponseEntity<String> result2 = restTemplate.exchange(url,
                                            HttpMethod.POST,
                                            requestEntity2,
                                            String.class);
        log.info("msg 카카옥 메시지 전송 성공....." + result2.toString());

    }
    public String messageString(){
        return "{\n" +
                "        \"object_type\": \"text\",\n" +
                "        \"text\": \"안녕하세요 %s 님 우리페이지 가입해 주셔서 감사합니다.\",\n" +
                "        \"link\": {\n" +
                "            \"web_url\": \"http://first.hellomh.site/first/test\",\n" +
                "            \"mobile_web_url\": \"http://first.hellomh.site/first/test\"\n" +
                "        },\n" +
                "        \"button_title\": \"바로 확인\"\n" +
                "    }";
    }
}
