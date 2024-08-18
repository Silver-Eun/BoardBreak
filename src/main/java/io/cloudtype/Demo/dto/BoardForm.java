package io.cloudtype.Demo.dto;

import io.cloudtype.Demo.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor // 생성자 대체
@ToString // toString 대체
@Data // getter, setter, ToString 대체
@Builder
public class BoardForm {
    private Long id;
    private String title;
    private String content;
    private String author;
    private Date createdAt;

    public Board toEntity() {
        return new Board(id, title, content, author, createdAt);
    }
}
