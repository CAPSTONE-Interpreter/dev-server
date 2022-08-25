package chamsae.koreansignlanguage.DTO;

import chamsae.koreansignlanguage.entity.Member;
import chamsae.koreansignlanguage.entity.Video;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScrapDTO {

    private long memId;
    private long vidId;
}
