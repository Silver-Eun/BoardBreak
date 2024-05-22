package com.board.boardBreak.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // DB가 해당 객체를 인식
@AllArgsConstructor // 필드 생성자 대체
@NoArgsConstructor // 기본 생성자 대체
@ToString // toString 대체
//@Access(AccessType.FIELD) 객체 지향 프로그래밍의 원칙을 위배
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

    // Getter 및 Setter 메서드 추가
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
