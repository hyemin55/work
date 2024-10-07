package com.pmh.org.freeboard;

import com.pmh.org.error.BizException;
import com.pmh.org.error.ErrorCode;
import com.pmh.org.file.FileEntity;
import com.pmh.org.file.FileRepository;
import com.pmh.org.user.User;
import com.pmh.org.user.UserRepository;
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
    public ResponseEntity<FreeBoardResponsePageDto> findAll(
            @RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "idx");

        Pageable pageable = PageRequest.of(pageNum, size, sort);
        Page<FreeBoard> page = freeBoardRepository.findAll(pageable);

//        System.out.println("elements = " + page.getTotalElements());
//        System.out.println("pages = " + page.getTotalPages());

        FreeBoardResponsePageDto freeBoardResponsePageDto
                = modelMapper
                .map(page, FreeBoardResponsePageDto.class);

//        List<FreeBoardResponseDto> list = new ArrayList<>();
//        for (FreeBoard freeBoard : freeBoardResponsePageDto.getContent()) {
//            FreeBoardResponseDto freeBoardResponseDto
//                    = modelMapper
//                    .map(freeBoard, FreeBoardResponseDto.class);
        List<FreeBoardResponseDto> list = freeBoardResponsePageDto
                .getContent()
                .stream()
                .map(freeBoard -> {
                    FreeBoardResponseDto freeBoardResponseDto = modelMapper.map(freeBoard, FreeBoardResponseDto.class);

                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy/MM/dd hh:mm");
                    freeBoardResponseDto.setRegDate(dateTimeFormatter.format(freeBoard.getRegDate()));
                    freeBoardResponseDto.setModDate(dateTimeFormatter.format(freeBoard.getModDate()));

                    if (freeBoard.getUser() != null) {
                        freeBoardResponseDto.setCreAuthor(freeBoard.getUser().getName());
                        freeBoardResponseDto.setModAuthor(freeBoard.getUser().getName());
                        freeBoardResponseDto.setUserIdx(freeBoard.getUser().getIdx());
                    } else {
                        freeBoardResponseDto.setCreAuthor("탈퇴한 회원");
                        freeBoardResponseDto.setModAuthor("탈퇴한 회원");
                    }
                    return freeBoardResponseDto;

                }).toList();

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
//            data들이 들어온다.
        produces = MediaType.APPLICATION_JSON_VALUE,
//            img파일이 들어온다.
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
//        freeboard의 user 와 user의 user를 연결
    User user = userRepository.findById(1l).orElse(new User());
    freeBoard.setUser(user);

    if (file != null) {
//            경로생성
        String myFilePath = Paths.get("images/file/").toAbsolutePath() + File.separator + file.getOriginalFilename();
        try {
//                파일생성
            File destFile = new File(myFilePath);
//                프론트에서 업로드한 파일을 destFile경로가 지정되어 있는 곳으로 transfer한다.
            file.transferTo(destFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

//            이제부터 constacet에 올리기 시작(결제 전 장바구니라고 생각하면 된다.)
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(file.getOriginalFilename());
        fileEntity.setPath(Paths.get("images/file/").toAbsolutePath().toString());
        fileEntity.setFreeBoard(freeBoard);
        fileRepository.save(fileEntity);
        freeBoard.setList(Arrays.asList(fileEntity));
        freeBoardRepository.save(freeBoard);
    } else {
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
    FreeBoard freeBoard = freeBoardRepository.findById(idx)
            .orElseThrow(() -> new BizException(ErrorCode.NOT_FOUND));

//        freeBoard.setList(new ArrayList<>());
//        Entity에 casecade로 연결되어있기 때문에 user idx 참조키가 연결되어 freeBoard list의 같은 user의 글을 모두 삭제하게된다.
//        그걸 방지하기 위해 user의 값을 null로 바꿔준다.
    freeBoard.setUser(null);
    freeBoardRepository.save(freeBoard);
    freeBoardRepository.delete(freeBoard);

//        fileRepository.findByFreeBoardIdx(
//                freeBoard.getIdx()).forEach(fileEntity -> {
//            fileRepository.deleteById(fileEntity.getIdx());
//        });
//        freeBoardRepository.cusDeleteByIdx(idx);

    return ResponseEntity.ok("삭제되었습니다.");
}
}
