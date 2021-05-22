package chamsae.koreansignlanguage.controller;

import chamsae.koreansignlanguage.domain.Video;
import chamsae.koreansignlanguage.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
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
    public Map<String, String> searchByText(@RequestParam("text") String text){
        log.info("videos 실행 - 텍스트 검색 : {}", text);
        HashMap<String, String> result = new HashMap<>();
        List<Video> list = videoService.searchByText(text);
        list.forEach(video -> result.put(video.getTitle(), video.getUrl()));
        return result;
    }

    @PostMapping("videos/photo")
    @ResponseBody
    public Map<String, Map> searchByPhoto(@RequestPart("file") MultipartFile file) {
        log.info("videos/photo 실행 - 사 검색 : {}", file);
        return videoService.searchByPhoto(file);
    }

}
