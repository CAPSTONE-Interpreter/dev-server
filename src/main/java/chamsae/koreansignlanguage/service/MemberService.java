package chamsae.koreansignlanguage.service;

import chamsae.koreansignlanguage.DTO.MemberDTO;
import chamsae.koreansignlanguage.entity.Member;
import chamsae.koreansignlanguage.error.EmailCheckException;
import chamsae.koreansignlanguage.mapper.MemberMapper;
import chamsae.koreansignlanguage.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    //TODO
    /*
     * 회원 등록
     * 회원 조회
     * 회원 삭제
     * 회원정보 업데이트
     * 중복 이메일 체크
     */

    final private MemberRepository memberRepository;

    private MemberMapper mapper = Mappers.getMapper(MemberMapper.class);

    //회원 등록
    public Member registerMem(MemberDTO memberDTO) {

        //실패 1. 해당 이메일로 가입된 정보가 있음.
        //그렇다면 exception 발생 -> bad request 떨어짐
        checkEmail(memberDTO.getEmail());

        Member member = mapper.toEntity(memberDTO);
        return memberRepository.save(member);
    }

    //회원 조회
    public MemberDTO getMem(long id) {

        //실패 1. 해당 멤버 아이디가 존재하지 않음. 잘못된 접근.
        Member m = memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 ID가 존재하지 않습니다. ID : " + id));

        return mapper.toDto(m);
    }

    //회원 정보 수정
    public MemberDTO updateInfo(long id, MemberDTO memberDTO) {

        //실패 1. 수정할 멤버가 존재하지 않음
        Member m = memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 ID가 존재하지 않습니다. ID : " + id));

        m.update(memberDTO.getNickName(), memberDTO.getPwd());
        memberRepository.save(m);

        return mapper.toDto(m);
    }

    //회원 삭제
    public void deleteMem(long id) {

        Member m = memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 ID가 존재하지 않습니다. ID : " + id));

        memberRepository.deleteById(id);
    }

    //중복 이메일 체크
    public void checkEmail(String newMail) {

        //중복 시 exception 던지기
        memberRepository.findByEmail(newMail)
                .orElseThrow(EmailCheckException::new);
    }
//
//    public Boolean validatePassword(String pw1, String pw2) {
//        if (pw1.equals(pw2)) return true;
//        else return false;
//    }
//
//    public Boolean LogIn(String email, String pw) {
//        Member member = memberRepository.findByEmail(email);
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        if(encoder.matches(pw, member.getPwd())) return true;
//        else return false;
//    }
}
