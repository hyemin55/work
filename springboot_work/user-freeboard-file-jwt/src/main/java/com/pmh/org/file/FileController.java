package com.pmh.org.file;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@RestController
@RequestMapping("file")
@CrossOrigin
public class FileController {

    private final Path imagePath;
    private final FileRepository fileRepository;
    private final ModelMapper modelMapper;

    public FileController(FileRepository fileRepository, ModelMapper modelMapper) {
//        toAbsolutePath는 상대경로를 절대경로로 바꿔준다.
//        html에서 ./main/java/com.example/ex10/images/file/이랑 같다.
        this.imagePath = Paths.get("images/file").toAbsolutePath();
        this.fileRepository = fileRepository;
        this.modelMapper = modelMapper;

        try {
//            주어진 경로에 디렉토리를 생성한다. 중간경로에 디렉토리가 없으면 그 디렉토리까지 생성된다.
            Files.createDirectories(this.imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(this.imagePath);
    }

    @GetMapping("test")
    public String test() {
        return "test";
    }

    @PostMapping(value = "upload",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(@RequestPart(name = "file") MultipartFile file,
                         @RequestPart(name = "fileReqDto") FileReqDto fileReqDto) {
        try {

//            파일 이름 출력
           System.out.println("파일 이름 출력 "+file.getOriginalFilename());
//            파일 경로 출력
           System.out.println("파일 경로 출력 "+imagePath.toAbsolutePath().toString());

//            전체 경로+ 파일이름 출력
            String myFilePath = imagePath.toAbsolutePath() + File.separator + file.getOriginalFilename();
           System.out.println("전체 경로 "+myFilePath);

            File saveFile = new File(myFilePath);
            file.transferTo(saveFile);

            FileEntity fileEntity = modelMapper.map(fileReqDto, FileEntity.class);
            fileRepository.save(fileEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "upload";
    }

    @PostMapping(value = "uploads", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploads(@RequestPart(name = "files") MultipartFile[] files,
                          @RequestPart(name = "fileDto") HashMap<String, String> map) {
        System.out.println("일로오나");
        try{
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                String filePath = imagePath.toString()+File.separator+fileName;
                System.out.println(filePath);
                File dest = new File(filePath);
                file.transferTo(dest);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return "upload";

    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable(name = "fileName") String fileName) throws IOException {
        // 파일이 저장된 경로
        Path filePath = imagePath.resolve(fileName);
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            throw new FileNotFoundException("파일을 찾을 수 없습니다: " + fileName);
        }

        // 파일을 전송
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // 또는 MediaType.IMAGE_PNG 등으로 변경 가능
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

}
