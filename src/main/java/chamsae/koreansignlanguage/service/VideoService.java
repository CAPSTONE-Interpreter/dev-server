package chamsae.koreansignlanguage.service;

import chamsae.koreansignlanguage.domain.Video;
import chamsae.koreansignlanguage.repository.VideoRepository;
import chamsae.koreansignlanguage.util.ConnectWithFlask;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
     * 사진 서버간 전달 springToFlask
     */

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private ConnectWithFlask connectWithFlask;

    public List<Video> searchByText(String text) {
        return videoRepository.findByTitleContaining(text);
    }

    public Map<String, Map> searchByPhoto(MultipartFile files) {

        Map<String, Map> result = new HashMap<>();

        try {
            ArrayList<String> response = connectWithFlask.sendImage(files);

            for(String word : response) {
                Map<String, String> video = new HashMap<>();
                List<Video> list = videoRepository.findByTitleContaining(word);
                for (Video v :
                        list) {
                    video.put(v.getTitle(), v.getUrl());
                }
                result.put(word, video);
            }

            log.info("사진 검색 결과 최종 반환 : {}", result);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
