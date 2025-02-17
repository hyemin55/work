package com.example.org.kakao;

import com.example.org.filter.JWTUtils;
import com.example.org.kakao.dto.KakaoMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kakao")
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService kakaoService;
    private final JWTUtils jwtUtils;

    @GetMapping("login")
    public ResponseEntity<String> kakaoCode(@RequestParam(value = "code") String code) {

//        log.debug("code{}", code);

        // 1. restTemplate
       String jwt = kakaoService.getToken(code);
        if(jwt.equals("fail"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fail to get jwt");
        else
            return ResponseEntity.ok(jwt);

    }


    @GetMapping("msg")
    public ResponseEntity<String> messageSend(@RequestParam(value = "message") String message,
                             @RequestHeader(value = "Authorization", required = false) String authorization) {

//        log.info(authorization);
        try{
            String jwt = authorization.split("Bearer ")[1];
//            String email = jwtUtils.getEmailFromJwt(jwt);
            kakaoService.messageSend(jwt, message);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("jwt empty");
        }
//        jwt
//        kakaoService.messageSend(email, message);
        return ResponseEntity.ok("message send success");
    }


//    public String templateString() {
//        return "{\n" +
//                "        \"object_type\": \"feed\",\n" +
//                "        \"content\": {\n" +
//                "            \"title\": \"오늘의 MSA\",\n" +
//                "            \"description\": \"내용 바꾸기... 내일 더 해볼꼐요... \",\n" +
//                "            \"image_url\": \"https://mud-kage.kakao.com/dn/NTmhS/btqfEUdFAUf/FjKzkZsnoeE4o19klTOVI1/openlink_640x640s.jpg\",\n" +
//                "            \"image_width\": 640,\n" +
//                "            \"image_height\": 640,\n" +
//                "            \"link\": {\n" +
//                "                \"web_url\": \"http://www.daum.net\",\n" +
//                "                \"mobile_web_url\": \"http://m.daum.net\",\n" +
//                "                \"android_execution_params\": \"contentId=100\",\n" +
//                "                \"ios_execution_params\": \"contentId=100\"\n" +
//                "            }\n" +
//                "        },\n" +
//                "        \"item_content\" : {\n" +
//                "            \"profile_text\" :\"Kakao\",\n" +
//                "            \"profile_image_url\" :\"https://mud-kage.kakao.com/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png\",\n" +
//                "            \"title_image_url\" : \"https://mud-kage.kakao.com/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png\",\n" +
//                "            \"title_image_text\" :\"Cheese cake\",\n" +
//                "            \"title_image_category\" : \"Cake\",\n" +
//                "            \"items\" : [\n" +
//                "                {\n" +
//                "                    \"item\" :\"Cake1\",\n" +
//                "                    \"item_op\" : \"1000원\"\n" +
//                "                },\n" +
//                "                {\n" +
//                "                    \"item\" :\"Cake2\",\n" +
//                "                    \"item_op\" : \"2000원\"\n" +
//                "                },\n" +
//                "                {\n" +
//                "                    \"item\" :\"Cake3\",\n" +
//                "                    \"item_op\" : \"3000원\"\n" +
//                "                },\n" +
//                "                {\n" +
//                "                    \"item\" :\"Cake4\",\n" +
//                "                    \"item_op\" : \"4000원\"\n" +
//                "                },\n" +
//                "                {\n" +
//                "                    \"item\" :\"Cake5\",\n" +
//                "                    \"item_op\" : \"5000원\"\n" +
//                "                }\n" +
//                "            ],\n" +
//                "            \"sum\" :\"Total\",\n" +
//                "            \"sum_op\" : \"15000원\"\n" +
//                "        },\n" +
//                "        \"social\": {\n" +
//                "            \"like_count\": 100,\n" +
//                "            \"comment_count\": 200,\n" +
//                "            \"shared_count\": 300,\n" +
//                "            \"view_count\": 400,\n" +
//                "            \"subscriber_count\": 500\n" +
//                "        },\n" +
//                "        \"buttons\": [\n" +
//                "            {\n" +
//                "                \"title\": \"웹으로 이동\",\n" +
//                "                \"link\": {\n" +
//                "                    \"web_url\": \"http://www.naver.com\",\n" +
//                "                    \"mobile_web_url\": \"http://m.naver.com\"\n" +
//                "                }\n" +
//                "            },\n" +
//                "            {\n" +
//                "                \"title\": \"앱으로 이동\",\n" +
//                "                \"link\": {\n" +
//                "                    \"android_execution_params\": \"contentId=100\",\n" +
//                "                    \"ios_execution_params\": \"contentId=100\"\n" +
//                "                }\n" +
//                "            }\n" +
//                "        ]\n" +
//                "    }";
//    }
}