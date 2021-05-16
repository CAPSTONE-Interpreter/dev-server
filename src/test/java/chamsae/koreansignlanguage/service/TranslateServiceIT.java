package chamsae.koreansignlanguage.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TranslateServiceIT {

    @Autowired
    private TranslateService translateService;

    @Test
    void 글자_합치기() {
        String word = "ㅇㅏㄴㄴㅕㅇ";
        String result = translateService.springToFlask(word);
        Assertions.assertThat(result).isEqualTo("안녕");
    }
}
