package chamsae.koreansignlanguage.controller;

import chamsae.koreansignlanguage.service.ScrapService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("scraps")
    @ResponseBody
    public JSONArray getScrap(@RequestParam("email") String email) {
        log.info("scrap 실행 - 해당 이메일 스크랩 리스트 불러오기 : {}", email);
        return scrapService.searchByEmail(email);
    }

    @PutMapping("scraps/{email}/{videoId}")
    @ResponseBody
    public void addToScrap(@PathVariable("email") String email, @PathVariable("videoId") int videoId) {
        log.info("scrap/{}/{} 실행 - 해당 스크랩 추가", email, videoId);
        scrapService.saveScrap(email, videoId);
    }

    @DeleteMapping("scraps/{email}/{videoId}")
    @ResponseBody
    public void deleteFromScrap(@PathVariable("email") String email, @PathVariable("videoId") int videoId) {
        log.info("scrap/{}/{} 실행 - 해당 스크랩 삭제", email, videoId);
        scrapService.deleteScrap(email, videoId);
    }
}
