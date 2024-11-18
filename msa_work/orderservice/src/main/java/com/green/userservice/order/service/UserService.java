package com.green.userservice.order.service;

import com.green.userservice.order.vo.UserRequest;
import com.green.userservice.order.vo.UserResponse;

public interface UserService {
    UserResponse join(UserRequest userRequest);

}
