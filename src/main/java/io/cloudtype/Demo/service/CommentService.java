package io.cloudtype.Demo.service;

import io.cloudtype.Demo.dto.CommentForm;
import io.cloudtype.Demo.entity.Board;
import io.cloudtype.Demo.entity.Comment;
import io.cloudtype.Demo.repository.BoardRepository;
import io.cloudtype.Demo.repository.CommentRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BoardRepository boardRepository;

    public List<CommentForm> Comments(Long boardId) {
//        // 댓글 목록 조회
//        List<Comment> commentlist =  commentRepository.findByBoardId(boardId);
//        // Entity -> DTO 변환
//        List<CommentForm> commentList = new ArrayList<CommentForm>();
//        for (int i = 0; i < commentlist.size(); i++) {
//            Comment c = commentlist.get(i);
//            CommentForm commentForm = CommentForm.createCommentDto(c);
//            commentList.add(commentForm);
//        }
//        // 반환
//        return commentList;
        return commentRepository.findByBoardId(boardId)
                .stream()
                .map(comment -> CommentForm.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentForm create(Long boardId, CommentForm commentForm) {
        // 게시글 조회 및 예외 처리
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalAccessError("댓글 생성 실패! 대상 게시글이 없습니다"));
        // 댓글 Entity 생성
        Comment comment = Comment.createComment(commentForm, board);
        // 댓글 Entity를 DB로 저장
        Comment created = commentRepository.save(comment);
        // DTO로 변경하여 변환
        return CommentForm.createCommentDto(created);
    }

    @Transactional
    public CommentForm update(Long id, CommentForm commentForm) {
        // 댓글 조회 및 예외 처리
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));
        // 댓글 수정
        target.patch(commentForm);
        // DB로 갱신
        Comment updated = commentRepository.save(target);
        // 댓글 Entity를 DTO로 변환 및 반환
        return CommentForm.createCommentDto(updated);
    }

    public CommentForm delete(Long id) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다"));
        // 댓글 삭제
        commentRepository.delete(target);
        // 삭제 댓글을 DTO로 변환
        return CommentForm.createCommentDto(target);
    }
}
