package projectnull.javaproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class ImageController {
    @PostMapping("/image")
    @ResponseBody
    public Map<String, Object> uploadImage(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        try {
            String uploadDir = "images/";
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs(); // 디렉토리가 존재하지 않으면 생성
            }

            // 원본 파일명 가져오기
            String originalFilename = file.getOriginalFilename();
            // 파일 이름이 중복되지 않도록 UUID 사용
            String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
            String filePath = uploadDir + uniqueFilename;

            // 파일 저장
            file.transferTo(new File(filePath));

            // 이미지 URL 설정
            String imageUrl = "/" + filePath;
            response.put("location", imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
            response.put("error", "Image upload failed");
        }

        return response;
    }
}
