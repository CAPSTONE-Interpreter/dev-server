package chamsae.koreansignlanguage.service;

import chamsae.koreansignlanguage.domain.Video;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class VideoServiceIntegrationTest {

    @Autowired
    private VideoService videoService;

    @Test
    public void 이름으로_동영상조회() {
        String title = "답";

        List<Video> result = videoService.searchByText(title);
        Assertions.assertThat(result.size()).isEqualTo(6);
    }
}
