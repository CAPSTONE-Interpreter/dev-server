package chamsae.koreansignlanguage.service;

import chamsae.koreansignlanguage.DTO.FindScrapsResDTO;
import chamsae.koreansignlanguage.DTO.ScrapDTO;
import chamsae.koreansignlanguage.entity.Member;
import chamsae.koreansignlanguage.entity.Scrap;
import chamsae.koreansignlanguage.entity.ScrapId;
import chamsae.koreansignlanguage.entity.Video;
import chamsae.koreansignlanguage.mapper.ScrapMapper;
import chamsae.koreansignlanguage.repository.MemberRepository;
import chamsae.koreansignlanguage.repository.ScrapRepository;
import chamsae.koreansignlanguage.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScrapService {

    final private ScrapRepository scrapRepository;
    final private VideoRepository videoRepository;
    final private MemberRepository memberRepository;

    private ScrapMapper mapper = Mappers.getMapper(ScrapMapper.class);

//    @Autowired
//    private MemberRepository

    //해당 회원 스크랩 목록 조회하기
    public FindScrapsResDTO findByMemId(long memId) {

        //실패 1. 해당 멤버가 DB에 존재하지 않음, 잘못된 uri
        Member m = memberRepository.findById(memId).orElseThrow(()->new IllegalArgumentException("해당 ID가 존재하지 않습니다. ID : " + memId));

        List<Long> scraps = scrapRepository.findByMemId(memId)
                .stream()
                .map(Scrap::getVidId)
                .collect(Collectors.toList());

        List<Video> videos = videoRepository.findAllById(scraps);
        int cnt = videos.size();

        //실패 2. 해당 멤버의 스크랩이 존재하지 않음.
        //cnt == 0이면 exception 던지기

        return new FindScrapsResDTO(memId, videos, videos.size());
    }


    public Scrap registerScrap(ScrapDTO scrapDTO) {

        long vidId = scrapDTO.getVidId();

        //실패 1. 해당 비디오
        Video v = videoRepository.findById(vidId)
                .orElseThrow(() -> new IllegalArgumentException("해당 비디오 ID가 존재하지 않습니다. ID : " + vidId));

        return scrapRepository.save(mapper.toEntity(scrapDTO));
    }

    public void deleteScrap(ScrapDTO scrapDTO) {
        scrapRepository.delete(mapper.toEntity(scrapDTO));
    }
}
