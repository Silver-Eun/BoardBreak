package com.board.boardBreak.service;

import com.board.boardBreak.entity.Board;
import com.board.boardBreak.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> findAll() {

        return boardRepository.findAll();
    }
}
