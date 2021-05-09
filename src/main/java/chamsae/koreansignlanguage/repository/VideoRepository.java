package chamsae.koreansignlanguage.repository;

import chamsae.koreansignlanguage.domain.Video;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VideoRepository {

    /*
    * 타이틀조회 findByTitle
    */

    public List<Video> findByTitle(String title) {
        return new ArrayList<>();
    }

    public List<Video> findById(Long id) {
        return new ArrayList<>();
    }
}
