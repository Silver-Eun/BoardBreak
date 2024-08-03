package io.cloudtype.Demo.repository;

import io.cloudtype.Demo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글의 모든 댓글 조회
    @Query(value =
            "SELECT * " +
            "FROM comment " +
            "WHERE board_id= :boardId", nativeQuery = true)
    List<Comment> findByBoardId(Long boardId);

    // 특정 사용자의 모든 댓글 조회
    List<Comment> findByNickname(String nickname);

    // 게시글 삭제시 댓글도 같이 삭제
    void deleteByBoardId(Long boardId);
}
