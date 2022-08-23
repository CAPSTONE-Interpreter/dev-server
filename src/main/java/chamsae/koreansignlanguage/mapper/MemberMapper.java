package chamsae.koreansignlanguage.mapper;

import chamsae.koreansignlanguage.DTO.MemberDTO;
import chamsae.koreansignlanguage.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring") //spring 에 맞게 bean 으로 등록해줌
public interface MemberMapper extends GenericMapper<MemberDTO, Member> {
}
