package chamsae.koreansignlanguage.controller;

import chamsae.koreansignlanguage.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Setter
public class MemberForm {

    private String nickName;
    private String email;
    private String password;
    private String password2;

    @Builder
    public MemberForm(String nickName, String email, String password, String password2) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.password2 = password2;
    }

    public Member toEntity() {
        return Member.builder()
                .nickName(nickName)
                .email(email)
                .password(new BCryptPasswordEncoder().encode(password))
                .build();
    }
}
