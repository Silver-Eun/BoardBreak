package io.cloudtype.Demo.service;

import io.cloudtype.Demo.entity.Board;
import io.cloudtype.Demo.repository.BoardRepository;
import io.cloudtype.Demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private BoardRepository boardRepository;
    private CommentRepository commentRepository;

    @Autowired
    public void setBoardRepository(BoardRepository boardRepository,
                                   CommentRepository commentRepository) {

        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
    }

    public List<Board> findAll() {

        return boardRepository.findAll();
    }

    public Board selectOne(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        if(board.isPresent()) return board.get();
        else return null;
    }

    @Transactional
    public void deleteBoardWithComments(Long id) {
        // 댓글 삭제
        commentRepository.deleteByBoardId(id);
        // 게시글 삭제
        boardRepository.deleteById(id);
    }
}
