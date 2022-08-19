package chamsae.koreansignlanguage.controller;

import chamsae.koreansignlanguage.DTO.MemberDTO;
import chamsae.koreansignlanguage.entity.Member;
import chamsae.koreansignlanguage.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

    //회원가입
    @PostMapping("members")
    public ResponseEntity<MemberDTO> join(@RequestBody MemberDTO memberDTO) {
        log.info("POST /member 실행 - 회원가입 : {}", memberDTO.toString());

        //회원가입 실패
        //1. 이미 가입된 메일로 가입 시도
//        if(!memberService.checkEmail(memberDTO))
//            return ResponseEntity
//                    .badRequest().
//                    .build();


        //2. 유저 정보가 제대로 넘어오지 않았음, 바디가 비어있음



        //회원가입 성공
        Member mem = memberService.join(memberDTO);
        //여기서 Member 객체를 쓰는게 맞나? Entity 는 서비스-디비 단에서 써야 하는거 아닌가?
        //id를 가져와서 uri 를 생성해주려면 Member 객체가 필요하긴 함.. (MemberDTO 에는 id 칼럼 없음)

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(mem.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    //로그인
//    @GetMapping("members")
//    public Boolean logIn(@RequestParam("email") String email, @RequestParam("password") String pw) {
//        log.info("members/login 실행 - 로그인 : {}", email);
//        return memberService.LogIn(email, pw);
//    }

//    public Boolean deleteId(String email, String pw) {
//        return true;
//    }

}
