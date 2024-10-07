package com.example.ex10.file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.File;
import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

//    select * from free_board_file where free_Board_idx=?
    List<FileEntity> findByFreeBoardIdx(long freeBoardIdx);

}
