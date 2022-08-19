package chamsae.koreansignlanguage.repository;

import chamsae.koreansignlanguage.entity.Scrap;
import chamsae.koreansignlanguage.entity.ScrapId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, ScrapId> {

    /*
    * 테이블에 저장 save
    * 테이블에서 삭제 delete
    * 테이블에서 가져오기 findAll
    */
    List<Scrap> findByEmail(String email);

}
