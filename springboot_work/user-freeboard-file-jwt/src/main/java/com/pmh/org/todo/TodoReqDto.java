package com.pmh.org.todo;

import com.pmh.org.user.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoReqDto {

    private Long idx;

    @NotEmpty
    private User user;

    @NotEmpty
    private String content;

    @NotEmpty
    private LocalDateTime tdate;
}
