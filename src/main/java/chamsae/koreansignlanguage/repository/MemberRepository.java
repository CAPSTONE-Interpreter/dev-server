package chamsae.koreansignlanguage.repository;

import chamsae.koreansignlanguage.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

    Member findByEmail(String email);

    Member findById(long id);
}
