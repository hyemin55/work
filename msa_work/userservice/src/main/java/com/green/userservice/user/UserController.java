package com.green.userservice.user;

import com.green.userservice.user.service.UserService;
import com.green.userservice.user.vo.UserRequest;
import com.green.userservice.user.vo.UserResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("join")
    public ResponseEntity<UserResponse> joinUser(@RequestBody UserRequest userRequest){

        UserResponse userResponse = userService.join(userRequest);

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("login")
    public ResponseEntity<String> getUser(){

        return ResponseEntity.ok(null);
    }

    @GetMapping("kakaologin")
    public ResponseEntity<String> kakaoLogin(){

        return ResponseEntity.ok(null);
    }
}