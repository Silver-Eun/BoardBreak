package com.board.boardBreak.dto;

import com.board.boardBreak.entity.Board;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 생성자 대체
@ToString // toString 대체
public class BoardForm {
    private String title;
    private String content;
    private String author;

    public Board toEntity() {

        return new Board(null, title, content, author);
    }
}
