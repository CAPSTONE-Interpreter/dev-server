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
import org.springframework.http.HttpStatus;
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
    @GetMapping("videos")
    public ResponseEntity findByText(@ApiParam(value = "검색할 단어", required = true) @RequestParam("text") String text){

        log.info("GET /videos?text={} 실행 - 텍스트 검색 : {}", text, text);

        Map<String, Object> result = videoService.findByText(text);

        //검색 실패
        //1. 검색에 대한 결과 리스트가 비어있음(검색 결과 없음)
        if((int)result.get("count") != 0)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("해당하는 검색어에 대한 결과가 없습니다.");

        //검색 성공
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "사진 인식", notes = "OCR 기능으로 해당 사진에 존재하는 텍스트를 반환합니다.")
    @PostMapping("videos/photos")
    public ResponseEntity searchByPhoto(@ApiParam(value = "첨부할 사진", required = true) @RequestPart("file") MultipartFile file) {
        log.info("POST /videos/photos 실행 - 사진 인식 : {}", file);


        List<String> result = videoService.findByPhoto(file);

        //인식 실패
        if(result.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("이미지로부터 글자를 인식하지 못했습니다.");
        }

        //인식 성공
        return ResponseEntity.ok().body(result);

    }

}
