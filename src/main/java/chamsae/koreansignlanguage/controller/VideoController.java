package chamsae.koreansignlanguage.controller;

import chamsae.koreansignlanguage.DTO.VideoDTO;
import chamsae.koreansignlanguage.entity.Video;
import chamsae.koreansignlanguage.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "2. Video")
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

    @ApiOperation(value = "텍스트 검색", notes = "제목을 입력하여 수어사전의 수어 비디오를 검색합니다.")
    @GetMapping("videos/{text}")
    public ResponseEntity<Map<String, Object>> findByText(@ApiParam(value = "검색할 단어", required = true) @RequestParam("text") String text){
        //검색 실패

        //검색 성공
        log.info("GET /videos/{} 실행 - 텍스트 검색 : {}", text, text);

        Map<String, Object> result = videoService.findByText(text);

        return ResponseEntity.ok().body(result);
    }

    @ApiOperation(value = "사진 검색", notes = "사진을 찍어 인식된 글자로 수어 비디를 검색합니다.")
    @PostMapping("videos/photos")
    public ResponseEntity<Map<String, Object>> searchByPhoto(@ApiParam(value = "첨부할 사진", required = true) @RequestPart("file") MultipartFile file) {
        //검색 실패

        //검색 성공
        log.info("POST /videos/photos 실행 - 사진 검색 : {}", file);

        Map<String, Object> result = videoService.findByPhoto(file);

        return ResponseEntity.ok().body(result);
    }

}
