package io.cloudtype.Demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.cloudtype.Demo.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor // 생성자 대체
@ToString // toString 대체
@Data // getter, setter, ToString 대체
public class CommentForm {
    private Long commentId;
    @JsonProperty("article_id")
    private Long boardId;
    private String memberId;
    private String content;

    public static CommentForm createCommentDto(Comment comment) {
        return new CommentForm(
                comment.getCommentId(),
                comment.getBoard().getId(),
                comment.getMemberId(),
                comment.getContent()
        );
    }
}
