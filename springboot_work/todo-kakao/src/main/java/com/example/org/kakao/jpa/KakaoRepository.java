package com.example.org.kakao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KakaoRepository extends JpaRepository<KakaoEntity, Long> {
    // JpaRepository<Entity, PK>{
}
