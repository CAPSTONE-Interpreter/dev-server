package chamsae.koreansignlanguage.repository;

import chamsae.koreansignlanguage.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    /*
    * 저장 save
    * 삭제 delete(일단 제외)
    * 이메일로조회 findByEmail
    */

    public Boolean save(Member member) {
        return true;
    }

    public Member findByEmail(String email) {
        Member m = new Member();
        return m;
    }
}
