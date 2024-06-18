package io.cloudtype.Demo.dto;

import io.cloudtype.Demo.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor // 생성자 대체
@ToString // toString 대체
@Data // getter, setter, ToString 대체
public class CommentForm {
    private int post_id;
    private int user_id;
    private String content;

    public Comment toEntity() {
        return new Comment(null, post_id, user_id, content, null);
    }
}
