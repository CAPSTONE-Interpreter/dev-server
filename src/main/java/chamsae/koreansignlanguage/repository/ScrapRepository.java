package chamsae.koreansignlanguage.repository;

import chamsae.koreansignlanguage.domain.Scrap;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ScrapRepository {

    /*
    * 테이블에 저장 save
    * 테이블에서 삭제 delete
    * 테이블에서 가져오기 findAll
    */

    public Boolean save(Scrap scrap) {
        return true;
    }

    public Boolean delete(Scrap scrap) {
        return true;
    }

    public List<Scrap> findAll(String email) {
        return new ArrayList<>();
    }
}
