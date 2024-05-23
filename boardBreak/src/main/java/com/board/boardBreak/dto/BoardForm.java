package com.board.boardBreak.dto;

import com.board.boardBreak.entity.Board;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor // 생성자 대체
@ToString // toString 대체
public class BoardForm {
    private String title;
    private String content;
    private String author;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created_at;

    public Board toEntity() {
        return new Board(null, title, content, author, created_at);
    }
}
