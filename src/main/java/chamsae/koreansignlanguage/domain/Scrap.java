package chamsae.koreansignlanguage.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
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
    private String email;
    @Id
    private int id;
}
