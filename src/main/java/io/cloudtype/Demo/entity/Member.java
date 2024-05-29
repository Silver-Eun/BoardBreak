package io.cloudtype.Demo.entity;

import lombok.*;
import javax.persistence.*;

@Entity // DB가 해당 객체를 인식
@AllArgsConstructor // 필드 생성자 대체
@NoArgsConstructor // 기본 생성자 대체
@Data // getter, setter, ToString 대체
public class Member {
    // Getter 및 Setter 메서드 추가
    @Id
    private String id;

    @Column
    private String password;
    @Column
    private String name;
}
