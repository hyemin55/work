package com.green.catalogservice.user.service;

import com.green.catalogservice.user.vo.UserRequest;
import com.green.catalogservice.user.vo.UserResponse;

public interface UserService {
    UserResponse join(UserRequest userRequest);

}
