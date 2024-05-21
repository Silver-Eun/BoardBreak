package com.board.boardBreak.dto;

import com.board.boardBreak.entity.Board;

public class BoardForm {
    private String title;
    private String content;

    public BoardForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Board toEntity() {
        return new Board(null, title, content);
    }
}
