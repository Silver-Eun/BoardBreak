package io.cloudtype.Demo.service;

import io.cloudtype.Demo.dto.CommentForm;
import io.cloudtype.Demo.entity.Comment;
import io.cloudtype.Demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<CommentForm> Comments(Long boardId) {
        // 댓글 목록 조회
        List<Comment> commentlist =  commentRepository.findByBoardId(boardId);
        // Entity -> DTO 변환
        List<CommentForm> commentList = new ArrayList<>();
        for (Comment c : commentlist) {
            CommentForm commentForm = CommentForm.createCommentDto(c);
            commentList.add(commentForm);
        }
        // 반환
        return commentList;
    }

}
