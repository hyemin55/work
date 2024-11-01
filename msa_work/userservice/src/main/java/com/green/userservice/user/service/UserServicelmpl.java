package com.green.userservice.user.service;

import com.green.userservice.user.jpa.UserEntity;
import com.green.userservice.user.jpa.UserRepository;
import com.green.userservice.user.vo.UserRequest;
import com.green.userservice.user.vo.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServicelmpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserResponse join(UserRequest userRequest){
        String reqEmail = userRequest.getEmail();

        userRepository.findByEmail(reqEmail)
                .ifPresent(user -> {
                    throw new IllegalArgumentException("이미 존재하는 이메일입니다. \n회원가입을 할 수 없습니다.\n%s", reqEmail);
                });


        ModelMapper mapper = new ModelMapper();
        UserEntity userEntity = mapper.map(userRequest, UserEntity.class);
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity = UserRepository.save(userEntity);
        UserResponse userResponse = mapper.map(userEntity, UserResponse.class);
        return userResponse;
    }
}
