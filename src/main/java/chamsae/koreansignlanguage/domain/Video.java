package chamsae.koreansignlanguage.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Video {

    @Id
    private int id;
    private String title;
    private String url;
}
