package chamsae.koreansignlanguage.service;

import chamsae.koreansignlanguage.util.ConnectWithFlask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslateService {

    @Autowired
    private ConnectWithFlask connectWithFlask;

    public String springToFlask(String word) {

        //실패 1. request 가 비어있음
        //if(word.isEmpty()) exception
        return connectWithFlask.sendLetters(word);
    }

}
