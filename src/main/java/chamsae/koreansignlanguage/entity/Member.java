package chamsae.koreansignlanguage.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="nickname")
    private String nickName;
    private String email;
    private String pwd;

    public void update(String nickName, String pwd) {

        if(nickName != null)
            this.nickName = nickName;
        if(pwd != null)
            this.pwd = pwd;
    }

    @Builder
    public Member(String nickName, String email, String pwd) {
        this.nickName = nickName;
        this.email = email;
        this.pwd = pwd;
    }
}
