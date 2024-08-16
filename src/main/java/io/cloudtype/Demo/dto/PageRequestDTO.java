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
    // 페이지
    private int page;
    // 1페이지당 글 개수
    private int size;

    public PageRequestDTO() {
        // 초기엔 1페이지
        this.page = 1;
        // 1페이지당 글 10개
        this.size = 10;
    }

    public Pageable getPageable(Sort sort) {
        // JPA는 페이지가 0부터 시작해서 page -1을 해줌
        return PageRequest.of(page -1, size, sort);
    }
}
