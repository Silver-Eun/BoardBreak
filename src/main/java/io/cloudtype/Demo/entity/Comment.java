package io.cloudtype.Demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity // DB가 해당 객체를 인식
@AllArgsConstructor // 필드 생성자 대체
@NoArgsConstructor // 기본 생성자 대체
@Data // getter, setter, ToString 대체
public class Comment {
    @Id // 대표값
    // 1, 2, 3...자동 생성 어노테이션
    // MySQL은 (strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int post_id;
    @Column
    private int user_id;
    @Column
    private String content;
    @Column
    //  @Column(updatable = false) 해당 필드가 처음 삽입된 이후로 업데이트되지 않도록
    //  @Temporal(TemporalType.TIMESTAMP)
    //  JPA에게 해당 필드가 java.util.Date나 java.util.Calendar 타입이며,
    //  이 필드를 데이터베이스의 날짜/시간 타입과 매핑해야 한다고 알림
    //  JPA는 기본적으로 적용
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created_at;

    @PrePersist
    //  JPA 엔티티가 영속성 컨텍스트에 처음으로 저장되기 전에 호출될 메서드에 적용
    protected void onCreate() {
        this.created_at = new Date();
    }
}
