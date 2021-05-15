package chamsae.koreansignlanguage.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Member {

    @Column(name="nickname")
    private String nickName;
    @Id
    private String email;
    private String password;

    @Builder
    public Member(String nickName, String email, String password) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
    }
}
