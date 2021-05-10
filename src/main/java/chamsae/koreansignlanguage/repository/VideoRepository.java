package chamsae.koreansignlanguage.repository;

import chamsae.koreansignlanguage.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Integer> {

    List<Video> findByTitleContaining(String title);
}
