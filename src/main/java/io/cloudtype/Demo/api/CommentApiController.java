package io.cloudtype.Demo.api;

import io.cloudtype.Demo.dto.CommentForm;
import io.cloudtype.Demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    // 게시글별 댓글 목록 조회
    @GetMapping("/api/board/{boardId}/comments")
    public ResponseEntity<List<CommentForm>> commentList(@PathVariable Long boardId) {
        // 서비스에게 위임
        List<CommentForm> comment = commentService.Comments(boardId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }
}
