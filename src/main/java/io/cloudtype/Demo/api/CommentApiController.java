package io.cloudtype.Demo.api;

import io.cloudtype.Demo.dto.CommentForm;
import io.cloudtype.Demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    // 게시글별 댓글 목록 조회
    @GetMapping("/api/board/{boardId}/comments")
    public ResponseEntity<List<CommentForm>> commentList(@PathVariable Long boardId) {
        // 서비스에게 위임
        List<CommentForm> comment = commentService.comments(boardId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }

    // 댓글 작성
    @PostMapping("/api/board/{boardId}/comments")
    public ResponseEntity<CommentForm> create(@PathVariable Long boardId,
                                              @RequestBody CommentForm commentForm) {
        // 서비스에게 위임
        CommentForm createDto = commentService.create(boardId, commentForm);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }

    // 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentForm> update(@PathVariable Long id,
                                              @RequestBody CommentForm commentForm) {
        // 서비스에게 위임
        CommentForm updateDto = commentService.update(id, commentForm);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }

    // 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentForm> delete(@PathVariable Long id) {
        // 서비스에게 위임
        CommentForm updatedDto = commentService.delete(id);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }
}
