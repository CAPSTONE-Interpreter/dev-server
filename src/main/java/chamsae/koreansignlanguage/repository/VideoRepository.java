package chamsae.koreansignlanguage.repository;

import chamsae.koreansignlanguage.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findByTitleContaining(String title);
}
