package chamsae.koreansignlanguage.service;

import chamsae.koreansignlanguage.domain.Video;
import chamsae.koreansignlanguage.repository.VideoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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

    public List<Video> searchByText(String text) {
        return videoRepository.findByTitle(text);
    }

    public List<Video> searchByPhoto(MultipartFile files) {
        //springToFlask 사용
        String text = "";
        return videoRepository.findByTitle(text);
    }

    public String springToFlask(String text) {
        String url = "http://76e4d8fcdce9.ngrok.io/hello/" + text; //get url text 파라미터 넣어서 보내
        String sb = "";
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();


            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

            String line = null;

            while ((line = br.readLine()) != null) {
                sb = sb + line + "\n";
            }
            br.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        log.info(sb.toString());
        return sb.toString();
    }
}
