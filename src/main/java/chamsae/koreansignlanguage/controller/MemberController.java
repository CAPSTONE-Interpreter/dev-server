package chamsae.koreansignlanguage.controller;

import chamsae.koreansignlanguage.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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

    public Boolean join(String nickName, String email, String pwd1, String pwd2) {
        return memberService.join(nickName, email, pwd1, pwd2);
    }

    public Boolean logIn(String email, String pwd) {
        return memberService.LogIn(email, pwd);
    }

//    public Boolean deleteId(String email, String pwd) {
//        return true;
//    }

}
