package chamsae.koreansignlanguage.mapper;

import chamsae.koreansignlanguage.DTO.VideoDTO;
import chamsae.koreansignlanguage.entity.Video;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoMapper extends GenericMapper<VideoDTO, Video> {
}
