package io.cloudtype.Demo.service;

import io.cloudtype.Demo.entity.Board;
import io.cloudtype.Demo.repository.BoardRepository;
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

    public void deleteById(Long id) {

        boardRepository.deleteById(id);
    }
}
