package io.cloudtype.Demo.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
// 다양한 곳에서 사용을 위해 제네릭타입을 DTO, EN(entity)로 지정
public class PageResultDTO<DTO, EN> {
    private List<DTO> dtoList;

    // Function<EN, DTO> fn는 entity 객체를 DTO로 변환
    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {

        dtoList = result.stream().map(fn).collect(Collectors.toList());
    }
}
