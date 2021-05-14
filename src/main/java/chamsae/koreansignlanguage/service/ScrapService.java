package chamsae.koreansignlanguage.service;

import chamsae.koreansignlanguage.domain.Scrap;
import chamsae.koreansignlanguage.domain.Video;
import chamsae.koreansignlanguage.repository.ScrapRepository;
import chamsae.koreansignlanguage.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrapService {

    /*
     * 이메일로 스크랩 테이블 긁어오기 findAll(String email)
     * 스크랩 추가 addToScrap(String email, Long videoId)
     * 스크랩 삭제 deleteFromScrap(String email, Long videoId)
     */

    @Autowired
    private ScrapRepository scrapRepository;

    @Autowired
    private VideoRepository videoRepository;

//    public List<Video> findAll(String email) {
//        List<Scrap> list = scrapRepository.findAll(email);
//        //list의 id를 통해 video에서 찾음
//        int id = 0;
//        return videoRepository.findById(id).stream().findAny();
//    }

    public Boolean addToScrap(String email, Long videoId) {
        return scrapRepository.save(new Scrap());
    }

    public Boolean deleteFromScrap(String email, Long videoId) {
        return scrapRepository.delete(new Scrap());
    }
}
