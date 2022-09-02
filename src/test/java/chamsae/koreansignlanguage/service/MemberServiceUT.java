package chamsae.koreansignlanguage.service;

import chamsae.koreansignlanguage.entity.Member;
import chamsae.koreansignlanguage.error.EmailCheckException;
import chamsae.koreansignlanguage.mapper.MemberMapper;
import chamsae.koreansignlanguage.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class MemberServiceUT {

    @Mock
    private MemberRepository memberRepository;

    @Spy
    private MemberMapper memberMapper = Mappers.getMapper(MemberMapper.class);

    @InjectMocks
    private MemberService memberService;

    @DisplayName("멤버 등록 성공")
    @Test
    void registerMemSuccess () {

        //given
        given(memberRepository.save(any()))
                .willReturn(member);

        //when
        Member actual = memberService.registerMem(memberMapper.toDto(member));

        //then
        assertThat(actual, is(member));
        then(memberRepository)
                .should(times(1)).save(any());

    }

    @DisplayName("이미 존재하는 이메일로 등록 시도")
    @Test
    void registerMemError () {

        //given
        given(memberRepository.findByEmail(duplicated.getEmail()))
                .willReturn(Optional.of(member));

        //when
        memberService.registerMem(memberMapper.toDto(duplicated));

        //then
        then(memberRepository).should(never()).save(duplicated);

        //exception 의 종류 안따져도 되나??????
        //컨트롤러 단 테스트 할 때 써서 확인?
        /*
        willThrow(new EmailCheckException()).given(memberService).checkEmail(duplicated.getEmail());
        */
    }

    @DisplayName("멤버 조회")
    @Test
    void getMemSuccess () {

        //given
//        given(memberRepository.findById(any())).willReturn(Optional.of(member));
//
//        //when
//        Member actual = memberService.getMem(1);
//
//        //given
//        then

    }

    Member member = Member.builder()
            .email("test@test.com")
            .nickName("testNickName")
            .pwd("testPwd")
            .build();

    Member duplicated = Member.builder()
            .email("test@test.com")
            .nickName("testNickName")
            .pwd("testPwd")
            .build();
}
