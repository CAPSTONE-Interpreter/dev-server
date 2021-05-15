package chamsae.koreansignlanguage.controller;

import chamsae.koreansignlanguage.domain.Member;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberForm {

    private String nickName;
    private String email;
    private String password;
    private String password2;

    public Member toEntity() {
        return Member.builder()
                .nickName(nickName)
                .email(email)
                .password(new BCryptPasswordEncoder().encode(password))
                .build();
    }
}
