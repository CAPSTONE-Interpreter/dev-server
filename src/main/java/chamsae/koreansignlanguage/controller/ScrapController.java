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
    * 스크랩 조회
    * 스크랩 추가
    * 스크랩 삭제
    */
    @Autowired
    private ScrapService scrapService;

    @ApiOperation(value = "스크랩 조회", notes = "해당 회원의 스크랩 목록 조회합니다.")
    @GetMapping("scraps/{mem_id}")
    public ResponseEntity getScrap(@ApiParam(value = "회원 ID", required = true) @PathVariable("mem_id") long mem_id) {
        log.info("GET /scraps/{} 실행 - 해당 회원 스크랩 리스트 불러오기 : {}", mem_id, mem_id);

        try {
            Map<String, Object> result = scrapService.findByMemId(mem_id);

            //조회 실패
            //1. 멤버의 스크랩이 존재하지 않음
            if((int)result.get("count") == 0)
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("해당 멤버의 스크랩이 존재하지 않습니다.");

            //조회 성공
            return ResponseEntity.ok(result);

        } catch (IllegalArgumentException e) {

            //2. 조회하려는 멤버 아이디에 대한 멤버가 존재하지 않음
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("해당 멤버 아이디는 존재하지 않습니다. 잘못된 경로입니다.");
        }
    }


    @ApiOperation(value = "스크랩 추가", notes = "해당 회원의 스크랩 목록에 새로운 비디오를 추가합니다.")
    @PostMapping("scraps")
    public ResponseEntity registerScrap(@ApiParam(value = "스크랩 정보", required = true) @RequestBody ScrapDTO scrapDTO) {
        log.info("POST /scraps 실행 - 해당 스크랩 추가 (memId : {}, vidId : {})", scrapDTO.getMemId(), scrapDTO.getVidId());

        try {
            Scrap scrap = scrapService.registerScrap(scrapDTO);

            //스크랩 추가 성공
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(scrap.getMemId())
                    .toUri();

            return ResponseEntity.created(location).build();
        }
        catch (IllegalArgumentException e) {
            //추가 실패
            //1. 스크랩에 추가하려는 비디오 아이디가 존재하지 않음
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("해당 비디오 아이디는 존재하지 않습니다.");
        }

    }


    @ApiOperation(value = "스크랩 삭제", notes = "해당 스크랩을 삭제합니다.")
    @DeleteMapping("scraps")
    public ResponseEntity deleteScrap(@ApiParam(value = "스크랩 정보", required = true) @RequestBody ScrapDTO scrapDTO) {
        log.info("DELETE /scraps 실행 - 해당 스크랩 삭제 (memId : {}, vidId : {})", scrapDTO.getMemId(), scrapDTO.getVidId());
        scrapService.deleteScrap(scrapDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
