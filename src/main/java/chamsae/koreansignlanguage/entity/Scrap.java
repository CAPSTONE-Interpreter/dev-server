package chamsae.koreansignlanguage.entity;

import chamsae.koreansignlanguage.DTO.ScrapDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@IdClass(ScrapId.class)
public class Scrap {

//    @Id
//    @ManyToOne
//    @JoinColumn(name = "Member_email")
//    private Member member;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "Video_id")
//    private Video video;

    @Id
    private long memId;
    @Id
    private long vidId;

    @Builder
    public Scrap(long memId, long vidId) {
        this.memId = memId;
        this.vidId = vidId;
    }
}
