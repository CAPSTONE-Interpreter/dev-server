package chamsae.koreansignlanguage.service;

import chamsae.koreansignlanguage.entity.Video;
import chamsae.koreansignlanguage.repository.VideoRepository;
import chamsae.koreansignlanguage.util.ConnectWithFlask;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class VideoService {

    //TODO
    /*
     * 텍스트 영상 검색 searchByText -> 텍스트 검색 들어오면 비디오 리파짓토리에서 찾아서 줘야함
     * OCR 영상 검색 searchByPhoto -> 이미지 분석 서버로 전달 후 리스트 받아 해당 영상들 전달
     */

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private ConnectWithFlask connectWithFlask;

    //텍스트 검색
    public Map<String, Object> findByText(String text) {
        Map<String, Object> result = new HashMap<>();
        List<Video> videos = videoRepository.findByTitleContaining(text);

        result.put("text", text);
        result.put("data", videos);
        result.put("count", videos.size());

        return result;
    }

    //사진 검색
    public List<String> findByPhoto(MultipartFile file) {

        List<String> result = new ArrayList<>();

        try {
            result = connectWithFlask.sendImage(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

//    public JSONArray searchByPhoto(MultipartFile file) {
//
//        JSONArray result = new JSONArray();
//
//        try {
//            ArrayList<String> response = connectWithFlask.sendImage(file);
//            for(String text : response) {
//                JSONObject ocrResult = new JSONObject();
//                JSONArray searchResult = new JSONArray();
//                List<Video> list = videoRepository.findByTitleContaining(text);
//                if(!list.isEmpty()) {
//                    ocrResult.put("text", text);
//                    for (Video v : list) {
//                        JSONObject videoInfo = new JSONObject();
//                        videoInfo.put("title", v.getTitle());
//                        videoInfo.put("url", v.getUrl());
//                        videoInfo.put("id", v.getId());
//                        searchResult.add(videoInfo);
//                    }
//                    ocrResult.put("result", searchResult);
//                    result.add(ocrResult);
//                }
//            }
//
//            log.info("사진 검색 결과 최종 반환 : {}", result);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }

}
