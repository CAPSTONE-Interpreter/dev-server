package chamsae.koreansignlanguage.controller;

import chamsae.koreansignlanguage.service.TranslateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "4. Translate")
@Slf4j
@Controller
public class TranslateController {

    @Autowired
    private TranslateService translateService;

    //ㅇㅏㄴㄴㅕㅇ => 안녕
    @ApiOperation(value = "단어 조합", notes = "요청한 자모음을 합쳐, 단어를 만듭니다.")
    @GetMapping("translate")
    public ResponseEntity<String> makeWord(@ApiParam(value = "자모음", required = true) @RequestParam("word") String word) {

        //단어 조합 성공
        log.info("GET /translate?word={} 실행 - 단어 조합 : {}", word, word);
        return ResponseEntity.ok(translateService.springToFlask(word));
    }
}
