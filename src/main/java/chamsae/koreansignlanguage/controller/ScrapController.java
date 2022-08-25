package chamsae.koreansignlanguage.controller;

import chamsae.koreansignlanguage.DTO.MemberDTO;
import chamsae.koreansignlanguage.DTO.ScrapDTO;
import chamsae.koreansignlanguage.DTO.VideoDTO;
import chamsae.koreansignlanguage.entity.Scrap;
import chamsae.koreansignlanguage.service.ScrapService;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@Api(tags = "3. Scrap")
@Slf4j
@Controller
public class ScrapController {

    /*
    * 이메일로 스크랩 테이블 긁어오기 findAll(String email)
    * 스크랩 추가 addToScrap(String email, Long videoId)
    * 스크랩 삭제 deleteFromScrap(String email, Long videoId)
    */
    @Autowired
    private ScrapService scrapService;

    @ApiOperation(value = "스크랩 조회", notes = "해당 회원의 스크랩 목록 조회합니다.")
    @GetMapping("scraps/{mem_id}")
    public ResponseEntity<Map<String, Object>> getScrap(@ApiParam(value = "회원 ID", required = true) @PathVariable("mem_id") long mem_id) {
        log.info("GET /scraps/{} 실행 - 해당 회원 스크랩 리스트 불러오기 : {}", mem_id, mem_id);
        Map<String, Object> result = scrapService.findByMemId(mem_id);

        return ResponseEntity.ok(result);
    }


    @ApiOperation(value = "스크랩 추가", notes = "해당 회원의 스크랩 목록에 새로운 비디오를 추가합니다.")
    @PostMapping("scraps")
    public ResponseEntity<ScrapDTO> registerScrap(@ApiParam(value = "스크랩 정보", required = true) @RequestBody ScrapDTO scrapDTO) {
        log.info("POST /scraps 실행 - 해당 스크랩 추가 (memId : {}, vidId : {})", scrapDTO.getMemId(), scrapDTO.getVidId());
        Scrap scrap = scrapService.registerScrap(scrapDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(scrap.getMemId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    @ApiOperation(value = "스크랩 삭제", notes = "해당 스크랩을 삭제합니다.")
    @DeleteMapping("scraps")
    public ResponseEntity deleteScrap(@ApiParam(value = "스크랩 정보", required = true) @RequestBody ScrapDTO scrapDTO) {
        log.info("DELETE /scraps 실행 - 해당 스크랩 삭제 (memId : {}, vidId : {})", scrapDTO.getMemId(), scrapDTO.getVidId());
        scrapService.deleteScrap(scrapDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
