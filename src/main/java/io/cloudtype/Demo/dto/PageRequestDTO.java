package io.cloudtype.Demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder // 빌더클래스 생략 가능
@AllArgsConstructor
@Data
// 화면에서 전달되면 page, size라는 파라미터를 수집하는 클래스
public class PageRequestDTO {
    private int page; // 페이지 번호
    private int size; // 페이지당 글 개수

    public PageRequestDTO() {
        this.page = 1; // 기본 페이지 번호
        this.size = 10; // 기본 페이지당 글 개수
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }
}
