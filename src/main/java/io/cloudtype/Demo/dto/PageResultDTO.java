package io.cloudtype.Demo.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
// 다양한 곳에서 사용을 위해 제네릭타입을 DTO, EN(entity)로 지정
public class PageResultDTO<DTO, EN> {
    private List<DTO> dtoList; // DTO 리스트
    private int totalPage;     // 총 페이지 수
    private int page;          // 현재 페이지
    private int size;          // 페이지당 글 개수

    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
        this.dtoList = result.stream().map(fn).collect(Collectors.toList());
        this.totalPage = result.getTotalPages();
        this.page = result.getNumber() + 1; // 페이지는 0부터 시작하므로 +1
        this.size = result.getSize();
    }
}
