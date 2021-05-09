package chamsae.koreansignlanguage.controller;

import chamsae.koreansignlanguage.service.TranslateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
public class TranslateController {

    @Autowired
    private TranslateService translateService;

    @PostMapping("translate")
    @ResponseBody
    public String makeSentence(List<String> list) {
        return translateService.springToFlask(list);
    }
}
