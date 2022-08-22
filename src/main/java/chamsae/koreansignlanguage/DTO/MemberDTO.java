package chamsae.koreansignlanguage.DTO;

import chamsae.koreansignlanguage.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {

    private String nickName;
    private String email;
    private String pwd;

    public Member toEntity() {
        return Member.builder()
                .nickName(nickName)
                .email(email)
                .pwd(pwd)
                .build();
    }
}
