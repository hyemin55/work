package com.example.org.todo;

import com.example.org.kakao.jpa.KakaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

//    select * from todo where kakao_id = ?
    List<TodoEntity> findAllByKakaoEntity(KakaoEntity kakaoEntity);

//    @Query("select * from todo where kakao_id = ?")
}
