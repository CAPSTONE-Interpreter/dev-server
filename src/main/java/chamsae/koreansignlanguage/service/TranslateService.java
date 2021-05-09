package chamsae.koreansignlanguage.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslateService {

    public String springToFlask(List<String> list) {
        //http 통신으로 post 하여 합쳐진 문장 받아옴
        return "성공";
    }

}
