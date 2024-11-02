package io.cloudtype.Demo.service;

import io.cloudtype.Demo.dto.BoardForm;
import io.cloudtype.Demo.dto.PageRequestDTO;
import io.cloudtype.Demo.dto.PageResultDTO;
import io.cloudtype.Demo.entity.Board;
import io.cloudtype.Demo.repository.BoardRepository;
import io.cloudtype.Demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;
import java.util.Optional;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    // 전체 게시글 불러오기
//        public List<Board> findAll() {
//
//        return boardRepository.findAll();
//    }
    public PageResultDTO<BoardForm, Board> getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());
        Page<Board> result = boardRepository.findAll(pageable);

        // 엔티티(Board)를 DTO(BoardForm)로 변환
        Function<Board, BoardForm> fn = (entity -> BoardForm.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .author(entity.getAuthor())
                .createdAt(entity.getCreatedAt())
                .build());

        return new PageResultDTO<>(result, fn);
    }

    private CommentRepository commentRepository;

    @Autowired
    public void setBoardRepository(BoardRepository boardRepository,
                                   CommentRepository commentRepository) {

        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
    }

    // 게시글 상세보기
    public Board selectOne(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        if(board.isPresent()) return board.get();
        else return null;
    }

    // 댓글 / 게시글 삭제
    @Transactional
    public void deleteBoardWithComments(Long id) {
        // 댓글 삭제
        commentRepository.deleteByBoardId(id);
        // 게시글 삭제
        boardRepository.deleteById(id);
    }
}
