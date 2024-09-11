package f_thon.godlifebingo.common.security.controller;

import f_thon.godlifebingo.common.annotation.CurrentUserId;
import java.io.IOException;
import java.nio.file.Files;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/login")
    public ResponseEntity<byte[]> login() throws IOException {
        // login.html 파일을 ClassPathResource로 로드
        ClassPathResource resource = new ClassPathResource("static/login.html");
        byte[] htmlContent = Files.readAllBytes(resource.getFile().toPath());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "text/html; charset=UTF-8");

        return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
    }

    @GetMapping("/")
    @ResponseBody
    public String main() {
        return "main";
    }


    @GetMapping("/mypage")
    @ResponseBody
    public String mypage(@CurrentUserId Long userId) {
        System.out.println(userId);
        return "mypage";
    }
}
