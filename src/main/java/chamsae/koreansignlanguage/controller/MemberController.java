package chamsae.koreansignlanguage.controller;

import chamsae.koreansignlanguage.DTO.MemberDTO;
import chamsae.koreansignlanguage.entity.Member;
import chamsae.koreansignlanguage.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        log.info("POST /members 실행 - 회원등록 이메일 : {}", memberDTO.getEmail());

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
    @GetMapping("members/{mem_id}")
    public ResponseEntity<MemberDTO> getMem(@ApiParam(value = "회원 ID", required = true) @PathVariable("mem_id") long mem_id) {
        log.info("GET /members/{} 실행 - 회원 조회 : {}", mem_id, mem_id);

        //조회 성공
        return ResponseEntity.ok(memberService.getMem(mem_id));

    }


    @ApiOperation(value = "회원 정보 수정", notes = "회원의 정보를 수정합니다.")
    @PutMapping("members/{mem_id}")
    public ResponseEntity<MemberDTO> updateInfo(@ApiParam(value = "회원 ID", required = true) @PathVariable("mem_id") long mem_id,
                                                @ApiParam(value = "수정할 정보", required = true) @RequestBody MemberDTO memberDTO) {
        log.info("PUT /members/{} 실행 - 회원 정보 수정 : {}", mem_id, mem_id);
        return ResponseEntity.ok(memberService.updateInfo(mem_id, memberDTO));
    }


    @ApiOperation(value = "회원 삭제", notes = "회원을 삭제합니다.")
    @DeleteMapping("members/{mem_id}")
    public ResponseEntity deleteMem(@ApiParam(value = "회원 ID", required = true) @PathVariable long mem_id) {
        log.info("DELETE members/{} 실행 - 회원 삭제 : {}", mem_id, mem_id);

        memberService.deleteMem(mem_id);
        return ResponseEntity.status(HttpStatus.OK).body("삭제가 완료되었습니다.");

    }

}
