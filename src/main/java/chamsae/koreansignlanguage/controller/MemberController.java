package chamsae.koreansignlanguage.controller;

import chamsae.koreansignlanguage.DTO.MemberDTO;
import chamsae.koreansignlanguage.entity.Member;
import chamsae.koreansignlanguage.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Api(tags = {"1. Member"})
@Slf4j
@Controller
public class MemberController {

    //TODO
    /*
    * 등록
    * 조회
    * 삭제
    * 정보수정
    */

    @Autowired
    private MemberService memberService;

    //회원 등록
    @ApiOperation(value = "회원 등록", notes = "회원을 등록합니다.")
    @PostMapping("members")
    public ResponseEntity<MemberDTO> registerMem(@ApiParam(value = "회원 정보", required = true) @RequestBody MemberDTO memberDTO) {
        log.info("POST /members 실행 - 회원등록 : {}", memberDTO.toString());

        //등록 실패
        //1. 이미 가입된 메일로 가입 시도
//        if(!memberService.checkEmail(memberDTO))
//            return ResponseEntity
//                    .badRequest().
//                    .build();


        //2. 유저 정보가 제대로 넘어오지 않았음, 바디가 비어있음



        //등록 성공
        Member mem = memberService.registerMem(memberDTO);
        //여기서 Member 객체를 쓰는게 맞나? Entity 는 서비스-디비 단에서 써야 하는거 아닌가?
        //id를 가져와서 uri 를 생성해주려면 Member 객체가 필요하긴 함.. (MemberDTO 에는 id 칼럼 없음)

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(mem.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    //회원 조회
    @ApiOperation(value = "회원 조회", notes = "회원을 조회합니다.")
    @GetMapping("members/{id}")
    public ResponseEntity<MemberDTO> getMem(@ApiParam(value = "회원 ID", required = true) @PathVariable("id") long id) {
        log.info("members/{} 실행 - 회원 조회 : {}", id, id);

        //조회 실패
        //1. 없는 아이디로 접근

        //조회 성공
        return ResponseEntity.ok(memberService.getMem(id));
    }

    public Boolean deleteId(String email, String pw) {
        return true;
    }

}
