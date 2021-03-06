package chamsae.koreansignlanguage.controller;

import chamsae.koreansignlanguage.util.ConnectWithFlask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class TestController {

    @Autowired
    ConnectWithFlask connectWithFlask;

    @PostMapping("test")
    @ResponseBody
    public String Test(@RequestPart("files") MultipartFile files) {

        try {
            if (files.isEmpty()) {
                log.info("파일 없음");
                return "파일이 없음";
            }
            else {
                log.info("파일 있음");
                return "성공";
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "실패";
    }

    @PostMapping("post")
    public String postTextForTest(@RequestParam("key") String text) {
        if(text.isEmpty())
            return "실패";
        else {
            String response = connectWithFlask.sendLetters(text);
            log.info(response);
            return response;
        }
    }
}
