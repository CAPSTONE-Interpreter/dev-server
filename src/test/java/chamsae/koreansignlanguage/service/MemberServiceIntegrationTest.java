package chamsae.koreansignlanguage.service;

import chamsae.koreansignlanguage.controller.MemberForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;

    @Test
    void 회원가입() {
        //given
        String nickName = "n";
        String email = "jj@gmail.com";
        String pw = "1234";
        String pw2 = "1234";

        MemberForm memberForm = new MemberForm(nickName, email, pw, pw2);

        //when
        Boolean result = memberService.join(memberForm);

        //then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void 로그인() {
        String email = "tttt3333@gmail.com";
        String pw = "1234";

        Boolean result = memberService.LogIn(email, pw);

        Assertions.assertThat(result).isTrue();
    }
}
