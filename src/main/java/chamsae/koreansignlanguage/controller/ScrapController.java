package chamsae.koreansignlanguage.controller;

import chamsae.koreansignlanguage.domain.Video;
import chamsae.koreansignlanguage.service.ScrapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ScrapController {

    /*
    * 이메일로 스크랩 테이블 긁어오기 findAll(String email)
    * 스크랩 추가 addToScrap(String email, Long videoId)
    * 스크랩 삭제 deleteFromScrap(String email, Long videoId)
    */

    @Autowired
    private ScrapService scrapService;

    public List<Video> findAll(String email) {
        return scrapService.findAll(email);
    }

    public Boolean addToScrap(String email, Long videoId) {
        return scrapService.addToScrap(email, videoId);
    }

    public Boolean deleteFromScrap(String email, Long videoId) {
        return scrapService.deleteFromScrap(email, videoId);
    }
}
