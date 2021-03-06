package chamsae.koreansignlanguage.service;

import chamsae.koreansignlanguage.controller.MemberForm;
import chamsae.koreansignlanguage.domain.Member;
import chamsae.koreansignlanguage.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    //TODO
    /*
     * 회원가입 join
     * 이메일중복 확인 validateDuplicateEmail(일단 제외)
     * 비밀번호일치 확인 validatePassword
     * 암호화 encodePassword
     * 복호화 decodePassword
     * 로그인 logIn
     * 탈퇴 deleteId(일단 제외)
     */

    @Autowired
    private MemberRepository memberRepository;

    public Boolean join(MemberForm memberForm) {
        if(validatePassword(memberForm.getPassword(), memberForm.getPassword2())) {
            Member member = memberForm.toEntity();
            memberRepository.save(member);
            return true;
        }
        else return false; //비밀번호가 일치하지 않음
    }

    public Boolean validatePassword(String pw1, String pw2) {
        if (pw1.equals(pw2)) return true;
        else return false;
    }

    public Boolean LogIn(String email, String pw) {
        Member member = memberRepository.findByEmail(email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(pw, member.getPassword())) return true;
        else return false;
    }
}
