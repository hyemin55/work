package com.example.ex10.user;

import com.example.ex10.user.UserReqDto;

public interface UserService {

    public void insert(UserReqDto userReqDto);

    public void update(UserReqDto userReqDto);
}