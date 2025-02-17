package com.green.catalogservice.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {

    @Schema(hidden = true)
    private Long id;

    @Email
    @NotNull(message = "Email cannot be null")
    @Schema(example = "aaa@naver.com")
    private String email;

    private String userId;

    @Schema(example = "홍길동")
    private String name;

    @NotNull(message = "password cannot be null")
    @Size(min = 8, message = "password must be greater than 8")
    @Schema(example = "asdf1234")
    private String password;




}
