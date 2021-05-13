package chamsae.koreansignlanguage.controller;

import chamsae.koreansignlanguage.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class MemberController {

    //TODO
    /*
    * 회원가입 join
    * 로그인 logIn
    * 탈퇴 deleteId
    */

    @Autowired
    private MemberService memberService;

    @PostMapping("member/new")
    @ResponseBody
    public Boolean join(MemberForm memberForm) {
        log.info("member/new post 실행");
        return memberService.join(memberForm);
    }

//    public Boolean logIn(String email, String pwd) {
//        return memberService.LogIn(email, pwd);
//    }

//    public Boolean deleteId(String email, String pwd) {
//        return true;
//    }

}
