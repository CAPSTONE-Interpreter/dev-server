package chamsae.koreansignlanguage.controller;

import chamsae.koreansignlanguage.service.TranslateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class TranslateController {

    @Autowired
    private TranslateService translateService;

    @GetMapping("translate")
    @ResponseBody
    public String makeSentence(@RequestParam("word") String word) {
        return translateService.springToFlask(word);
    }
}
