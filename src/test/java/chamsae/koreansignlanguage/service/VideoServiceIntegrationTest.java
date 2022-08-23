package chamsae.koreansignlanguage.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class VideoServiceIntegrationTest {

    @Autowired
    private VideoService videoService;

//    @Test
//    public void 이름으로_동영상조회() {
//        String title = "미";
//
//        List<Map> result = videoService.searchByText(title);
//
//        System.out.println(result);
//    }
}
