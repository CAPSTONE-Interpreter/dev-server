package chamsae.koreansignlanguage.controller;

import chamsae.koreansignlanguage.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class VideoController {

    //TODO
    /*
    * 텍스트 영상 검색 searchByText
    * OCR 영상 검색 searchByPhoto
    */

    @Autowired
    private VideoService videoService;

    @GetMapping("videos")
    @ResponseBody
    public JSONArray searchByText(@RequestParam("text") String text){
        log.info("videos 실행 - 텍스트 검색 : {}", text);
        return videoService.searchByText(text);
    }

    @PostMapping("videos/photo")
    @ResponseBody
    public JSONArray searchByPhoto(@RequestPart("file") MultipartFile file) {
        log.info("videos/photo 실행 - 사진 검색 : {}", file);
        return videoService.searchByPhoto(file);
    }

}
