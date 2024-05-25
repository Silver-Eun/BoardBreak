package com.board.boardBreak.dto;

import com.board.boardBreak.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 생성자 대체
@ToString // toString 대체
public class MemberForm {
    private String id;
    private String password;
    private String name;

    public Member toEntity() {

        return new Member(id, password, name);
    }
}
