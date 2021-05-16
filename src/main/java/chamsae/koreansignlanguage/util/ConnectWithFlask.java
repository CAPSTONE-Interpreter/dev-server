package chamsae.koreansignlanguage.util;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

@Slf4j
@Component
public class ConnectWithFlask {

    public String springToFlask(String text) {
        StringBuilder sb = new StringBuilder();
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            String url = "http://52.78.237.179:5000/motion/" + URLEncoder.encode(text, "UTF-8");

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();


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

        log.info(jsonObject.get("result").toString());
        return jsonObject.get("result").toString();
    }

}
