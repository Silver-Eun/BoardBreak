package io.cloudtype.Demo.repository;

import io.cloudtype.Demo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
