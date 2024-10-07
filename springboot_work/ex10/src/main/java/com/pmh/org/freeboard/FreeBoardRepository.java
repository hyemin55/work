package com.pmh.org.freeboard;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface FreeBoardRepository extends JpaRepository<FreeBoard,Long> {

//   freeboard list의 게시물에 참조된 user의 연관관계 맵핑을 끊고 쿼리문으로 실행하는 방법이다.
    @Modifying
    @Transactional
    @Query(value = "delete * from freeBoard fb WHERE fb.idx = :idx", nativeQuery = true)
    void cusDeleteByIdx(@Param("idx") Long idx);
}
