package com.pmh.org.login;

import com.pmh.org.login.jwt.JWTManager;
import com.pmh.org.user.User;
import com.pmh.org.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class LoginController {

    private final LoginService loginService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTManager jwtManager;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody JoinDto joinDto) {
//
//        System.out.println("일로오나");
//        System.out.println(joinDto);
        loginService.join(joinDto);
        return ResponseEntity.ok("success");
    }

    // swagger 문서에서 사용가능하게..
    @GetMapping("/login")
    public ResponseEntity<String> redirectWithPost(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpServletResponse response) throws IOException {
        // 해당되는 email.이 있는지.
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(email)
        );

        boolean pwEquals = passwordEncoder.matches(password, user.getPassword());
        System.out.println(pwEquals);
        if (pwEquals) {
            String jwt = jwtManager.createJWT(user.getEmail(), user.getRole());
            return ResponseEntity.ok(jwt);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("이메일과 패스워드를 확인하세요");
        }
    }

    @GetMapping("/check")
    public ResponseEntity<User> check(
            @RequestParam("jwt") String jwt,
            HttpServletResponse response) throws IOException {

//        jwt가 유효한지 검사, 유효하지 않으면 갑자기 Exception 발생
        Jws<Claims> claimsJws = jwtManager.getClaims(jwt);
//        jwt가 유효하면 email 가져오기
        String email = claimsJws.getPayload().get("email").toString();
//        email로 DB 테이블 조회
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(email)
        );
        return ResponseEntity.ok(user);
    }
}
