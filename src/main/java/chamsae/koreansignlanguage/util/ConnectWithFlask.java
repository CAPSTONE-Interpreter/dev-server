package chamsae.koreansignlanguage.util;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ConnectWithFlask {
    final String FLASK_URL = "http://52.78.237.179:5000/";

    final String boundary = "*****";
    final String crlf = "\r\n";
    final String twoHyphens = "--";

    public String sendLetters(String text) {
        StringBuilder sb = new StringBuilder();
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            String sentenceUrl = FLASK_URL + "motion/" + URLEncoder.encode(text, "UTF-8");

            HttpURLConnection conn = (HttpURLConnection) new URL(sentenceUrl).openConnection();


            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String line = null;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            Object obj = parser.parse(sb.toString());
            jsonObject = (JSONObject) obj;
            br.close();

        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {

            e.printStackTrace();
        }

        log.info("텍스트 조합 완성 문자 : {}", jsonObject.get("result").toString());
        return jsonObject.get("result").toString();
    }

    public ArrayList<String> sendImage(MultipartFile file) throws IOException {

        String ocrUrl = FLASK_URL + "photo";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        ByteArrayResource resource = new ByteArrayResource(file.getBytes()){
            @Override
            public String getFilename() throws IllegalStateException {
                return file.getOriginalFilename();
            }
        };

        body.add("img", resource);

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JSONObject> response = restTemplate
                .postForEntity(ocrUrl, requestEntity, JSONObject.class);

        log.info("요청 return 값 : {}", response.getBody().get("words").toString());

        return (ArrayList<String>) response.getBody().get("words");
    }

}
