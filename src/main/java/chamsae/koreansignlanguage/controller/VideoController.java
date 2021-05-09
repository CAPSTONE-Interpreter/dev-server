package chamsae.koreansignlanguage.controller;

import chamsae.koreansignlanguage.domain.Video;
import chamsae.koreansignlanguage.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VideoController {

    //TODO
    /*
    * 텍스트 영상 검색 searchByText
    * OCR 영상 검색 searchByPhoto
    */

    @Autowired
    private VideoService videoService;

    @GetMapping
    @ResponseBody
    public List<Video> searchByText(String text){
        return videoService.searchByText(text);
    }

    public List<Video> searchByPhoto(MultipartFile files) {
        return videoService.searchByPhoto(files);
    }

}
