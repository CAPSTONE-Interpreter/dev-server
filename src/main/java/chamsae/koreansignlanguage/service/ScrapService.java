package chamsae.koreansignlanguage.service;

import chamsae.koreansignlanguage.DTO.ScrapDTO;
import chamsae.koreansignlanguage.entity.Member;
import chamsae.koreansignlanguage.entity.Scrap;
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
    public Map<String, Object> findByMemId(long memId) {
        Map<String, Object> result = new HashMap<>();
        List<Video> videos = new ArrayList<>();

        Optional<Member> m = memberRepository.findById(memId);
        m.orElseThrow(()->new IllegalArgumentException("해당 ID가 존재하지 않습니다. ID : " + memId));

        List<Scrap> scraps = scrapRepository.findByMemId(memId);

        if(!scraps.isEmpty()) {
            result.put("mem_id", memId);

            for(Scrap s : scraps) {
                Optional<Video> v = videoRepository.findById(s.getVidId());
                v.ifPresent(video -> videos.add(video));
            }

            result.put("data", videos);
            result.put("count", videos.size());
        }

        return result;
    }


    public Scrap registerScrap(ScrapDTO scrapDTO) {
        return scrapRepository.save(mapper.toEntity(scrapDTO));
    }

    public void deleteScrap(ScrapDTO scrapDTO) {
        scrapRepository.delete(mapper.toEntity(scrapDTO));
    }
}
