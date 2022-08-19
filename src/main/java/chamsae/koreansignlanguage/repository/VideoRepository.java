package chamsae.koreansignlanguage.repository;

import chamsae.koreansignlanguage.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Integer> {

    List<Video> findByTitleContaining(String title);

    Video findById(int id);
}
