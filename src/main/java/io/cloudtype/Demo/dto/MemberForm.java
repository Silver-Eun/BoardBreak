package io.cloudtype.Demo.dto;

import io.cloudtype.Demo.entity.Member;
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
