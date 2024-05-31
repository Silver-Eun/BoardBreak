package io.cloudtype.Demo.service;

import io.cloudtype.Demo.entity.Board;
import io.cloudtype.Demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private BoardRepository boardRepository;

    @Autowired
    public void setBoardRepository(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> findAll() {

        return boardRepository.findAll();
    }

    public Board selectOne(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        if(board.isPresent()) return board.get();
        else return null;
    }
}
