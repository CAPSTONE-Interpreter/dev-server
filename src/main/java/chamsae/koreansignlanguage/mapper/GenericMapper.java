package chamsae.koreansignlanguage.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface GenericMapper<D, E> {

    D toDto(E e);
    E toEntity(D d);

    //Source 의 필드가 null 일때 정책으로 null 값은 무시하고, null 이 아닌 값만 업데이트
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(D dto, @MappingTarget E entity);
    //반환하여 객체를 return 하는 것이 아니라 인자로 받아 업데이트를 할 target 을 정해주는 것
}
