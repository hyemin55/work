package com.example.org.todo;

import com.example.org.kakao.jpa.KakaoEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "todos")
public class TodoEntity {

    @Id
    private Long id;

    private String title;
    private String content;
    private boolean completed;
    private LocalDate selectDate;

    @ManyToOne(targetEntity = TodoEntity.class)
    private KakaoEntity kakaoEntity;
}
