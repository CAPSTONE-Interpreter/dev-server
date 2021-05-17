package chamsae.koreansignlanguage.controller;

import chamsae.koreansignlanguage.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("members/new")
    @ResponseBody
    public Boolean join(@RequestBody MemberForm memberForm) {
        log.info("member/new post 실행");
        return memberService.join(memberForm);
    }

    @PostMapping("members/login")
    @ResponseBody
    public Boolean logIn(@RequestParam("email") String email, @RequestParam("password") String pw) {
        log.info("members/login 실행");
        return memberService.LogIn(email, pw);
    }

//    public Boolean deleteId(String email, String pw) {
//        return true;
//    }

}
