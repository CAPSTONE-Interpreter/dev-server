package chamsae.koreansignlanguage.service;

import chamsae.koreansignlanguage.DTO.MemberDTO;
import chamsae.koreansignlanguage.entity.Member;
import chamsae.koreansignlanguage.mapper.MemberMapper;
import chamsae.koreansignlanguage.repository.MemberRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    //TODO
    /*
     * 회원 등록
     * 회원 조회
     * 회원 삭제
     * 회원정보 업데이트
     * 중복 이메일 체크
     */

    @Autowired
    private MemberRepository memberRepository;

    private final MemberMapper mapper = Mappers.getMapper(MemberMapper.class);

    //회원 등록
    public Member registerMem(MemberDTO memberDTO) {
            Member member = mapper.toEntity(memberDTO);
            return memberRepository.save(member);
    }

    //회원 조회
    public MemberDTO getMem(long id) {

        Member m = memberRepository.findById(id);
        return mapper.toDto(m);
    }


    //중복 이메일 체크
    public boolean checkEmail(MemberDTO memberDTO) {
        if(memberRepository.findByEmail(memberDTO.getEmail()) == null) return true; //가입 가능
        return false; //가입 불가능
    }

    public Boolean validatePassword(String pw1, String pw2) {
        if (pw1.equals(pw2)) return true;
        else return false;
    }

    public Boolean LogIn(String email, String pw) {
        Member member = memberRepository.findByEmail(email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(pw, member.getPwd())) return true;
        else return false;
    }
}
