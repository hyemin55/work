package com.example.ex10.freeboard;

import com.example.ex10.error.BizException;
import com.example.ex10.error.ErrorCode;
import com.example.ex10.file.FileEntity;
import com.example.ex10.file.FileRepository;
import com.example.ex10.user.User;
import com.example.ex10.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("freeboard")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j // log.info에 사용. sout보다 자세히(log내용들) 나온다.
public class FreeBoardController {

    private final FreeBoardRepository freeBoardRepository;
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    //    welcome 안에 application의 my.value의 값이 들어간다.
    @Value("$my.value")
    private String welcome;

    @GetMapping("test")
    public String test() {
        return welcome;
    }

    @GetMapping
    public ResponseEntity<FreeBoardResponsePageDto> findAll(@RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
                                                            @RequestParam(name = "size", defaultValue = "5") int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "idx");

        Pageable pageable = PageRequest.of(pageNum, size, sort);
        Page<FreeBoard> page = freeBoardRepository.findAll(pageable);

        System.out.println("elements = " + page.getTotalElements());
        System.out.println("pages = " + page.getTotalPages());

        FreeBoardResponsePageDto freeBoardResponsePageDto
                = new ModelMapper()
                .map(page, FreeBoardResponsePageDto.class);

        List<FreeBoardResponseDto> list = new ArrayList<>();
        for (FreeBoard freeBoard : freeBoardResponsePageDto.getContent()) {
            FreeBoardResponseDto freeBoardResponseDto
                    = new ModelMapper()
                    .map(freeBoard, FreeBoardResponseDto.class);

            DateTimeFormatter dateTimeFormatter
                    = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");

            freeBoardResponseDto.setCreAuthor(freeBoard.getUser().getName());
            freeBoardResponseDto.setRegDate(dateTimeFormatter.format(freeBoard.getRegDate()));
            freeBoardResponseDto.setModDate(dateTimeFormatter.format(freeBoard.getModDate()));

            list.add(freeBoardResponseDto);
        }
        freeBoardResponsePageDto.setList(list);
//        return  ResponseEntity.ok(list.getContent());
        return ResponseEntity.ok(freeBoardResponsePageDto);
    }

    @GetMapping("view/{idx}")
    public ResponseEntity<FreeBoardResponseDto> findOne(@PathVariable(name = "idx") long idx) {
        FreeBoard freeBoard = freeBoardRepository.findById(idx)
                .orElseThrow(() -> new BizException(ErrorCode.NOT_FOUND));

        freeBoard.setView_Count(freeBoard.getView_Count() + 1);
        freeBoardRepository.save(freeBoard);

        //        FreeboardController의 EAGER모드 사용할 때.
        System.out.println(freeBoard.getList());

        FreeBoardResponseDto freeBoardResponseDto = modelMapper.map(freeBoard, FreeBoardResponseDto.class);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy/MM/dd hh:mm");

        freeBoardResponseDto.setRegDate(dateTimeFormatter.format(freeBoard.getRegDate()));
        freeBoardResponseDto.setModDate(dateTimeFormatter.format(freeBoard.getModDate()));

        freeBoardResponseDto.setCreAuthor(freeBoard.getUser().getName());
        freeBoardResponseDto.setModAuthor(freeBoard.getUser().getName());
        freeBoardResponseDto.setUserIdx(freeBoard.getUser().getIdx());

        return ResponseEntity.ok(freeBoardResponseDto);
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )

//    @Transactional
    public ResponseEntity<FreeBoard> save(
            @Valid @RequestPart(name = "data") FreeBoardReqDto freeBoardReqDto,
            @RequestPart(name = "file", required = false) MultipartFile file) {

        FreeBoard freeBoard = modelMapper.map(freeBoardReqDto, FreeBoard.class);
        freeBoardRepository.save(freeBoard);
        // Todo...
        // 1번 사용자가 무조건 작성 한걸로..
        // jwt 로그인 하면 ... 로그인한 사용자를 넣을꺼예요
        User user = userRepository.findById(1l).orElse(new User());
        freeBoard.setUser(user);

        if (file != null) {
            String myFilePath = Paths.get("images/file/").toAbsolutePath() + File.separator + file.getOriginalFilename();
            try {
                File destFile = new File(myFilePath);
                file.transferTo(destFile);
            } catch (Exception e) {
                e.printStackTrace();
            }

            FileEntity fileEntity = new FileEntity();
            fileEntity.setName(file.getOriginalFilename());
            fileEntity.setPath(Paths.get("images/file/").toAbsolutePath().toString());
            fileEntity.setFreeBoard(freeBoard);
            fileRepository.save(fileEntity);
            freeBoard.setList(Arrays.asList(fileEntity));
            freeBoardRepository.save(freeBoard);
        }else{
            List<FileEntity> list = fileRepository.findByFreeBoardIdx(freeBoard.getIdx());
            list.forEach(fileEntity -> {
                // delete * from free_board_file where idx = ?
                fileRepository.deleteById(fileEntity.getIdx());
                fileRepository.flush();
            });
            freeBoard.setList(Arrays.asList());
            freeBoardRepository.save(freeBoard);
        }
        return ResponseEntity.status(200).body(freeBoard);
    }

    @DeleteMapping("delete/{idx}")
    public ResponseEntity<String> deleteByID(@PathVariable(name = "idx") long idx) {
        freeBoardRepository.findById(idx).orElseThrow(() -> new BizException(ErrorCode.NOT_FOUND));
        freeBoardRepository.deleteById(idx);

        return ResponseEntity.ok("삭제되었습니다.");
    }
}
