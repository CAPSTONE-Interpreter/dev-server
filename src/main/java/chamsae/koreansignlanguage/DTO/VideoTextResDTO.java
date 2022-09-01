package chamsae.koreansignlanguage.DTO;

import chamsae.koreansignlanguage.entity.Video;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class VideoTextResDTO {
    private String text;
    private List<Video> videos;
    private int count;
}
