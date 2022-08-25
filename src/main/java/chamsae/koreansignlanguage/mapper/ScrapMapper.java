package chamsae.koreansignlanguage.mapper;

import chamsae.koreansignlanguage.DTO.ScrapDTO;
import chamsae.koreansignlanguage.entity.Scrap;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScrapMapper extends GenericMapper<ScrapDTO, Scrap> {
}
