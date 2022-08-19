package chamsae.koreansignlanguage.service;

import chamsae.koreansignlanguage.entity.Scrap;
import chamsae.koreansignlanguage.entity.Video;
import chamsae.koreansignlanguage.repository.ScrapRepository;
import chamsae.koreansignlanguage.repository.VideoRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ScrapService {

    @Autowired
    private ScrapRepository scrapRepository;

    @Autowired
    private VideoRepository videoRepository;

    public JSONArray searchByEmail(String email) {

        JSONArray result = new JSONArray();

        List<Scrap> list = scrapRepository.findByEmail(email);

        for (Scrap s :
                list) {
            JSONObject videoInfo = new JSONObject();
            Video video = videoRepository.findById(s.getId());
            videoInfo.put("title", video.getTitle());
            videoInfo.put("url", video.getUrl());
            videoInfo.put("id", video.getId());
            result.add(videoInfo);
        }

        log.info("{} - 스크랩 리스트 : {}", email, result.toString());

        return result;
    }

    public void saveScrap(String email, int videoId) {

        Scrap scrap = new Scrap();
        scrap.setEmail(email);
        scrap.setId(videoId);

        scrapRepository.save(scrap);
        log.info("{} 스크랩 추가 완료: {}", email, videoId);
    }

    public void deleteScrap(String email, int videoId) {
        Scrap scrap = new Scrap();
        scrap.setEmail(email);
        scrap.setId(videoId);

        scrapRepository.delete(scrap);
        log.info("{} 스크랩 삭제 완료: {}", email, videoId);
    }
}
