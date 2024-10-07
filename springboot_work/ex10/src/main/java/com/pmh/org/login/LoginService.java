package com.pmh.org.login;

import com.pmh.org.user.User;
import com.pmh.org.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public void join(JoinDto joinDto) {

        User user = modelMapper.map(joinDto, User.class);
//        username -> name
//        user.setName(joinDto.getName());
//        암호화
        user.setPassword(passwordEncoder.encode(joinDto.getPassword()));

//        일단 관리자로만 로그인하겠다고 설정함.
        user.setRole("ROLE_ADMIN");

        User dbUser = userRepository.save(user);
        System.out.println(dbUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        해당되는 이메일이 있는지 데이터베이스에서 확인
//        없으면 throw로 UsernameNotFoundException이 실행된다.
        User user = userRepository.findByEmail(username).orElseThrow(
                ()->new UsernameNotFoundException(username));

//        password 가 1234이면 ADMIN 권한으로 로그인해라
//        username은 아무렇게나 들어와도 된다.
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles("ADMIN")
                .build();
    }
}
