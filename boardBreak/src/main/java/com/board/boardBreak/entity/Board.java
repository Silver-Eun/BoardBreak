package com.board.boardBreak.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity // DB가 해당 객체를 인식
@AllArgsConstructor // 생성자 대체
@ToString // toString 대체
public class Board {
    @Id // 대표값
    // 1, 2, 3...자동 생성 어노테이션
    // MySQL은 (strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String author;

    public Board() {

    }
}
