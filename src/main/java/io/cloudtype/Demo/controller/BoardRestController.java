package io.cloudtype.Demo.controller;

import io.cloudtype.Demo.dto.BoardForm;
import io.cloudtype.Demo.dto.PageRequestDTO;
import io.cloudtype.Demo.dto.PageResultDTO;
import io.cloudtype.Demo.entity.Board;
import io.cloudtype.Demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardRestController {

    private final BoardService boardService;

    @Autowired
    public BoardRestController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/api/board/list")
    public PageResultDTO<BoardForm, Board> getBoards(PageRequestDTO pageRequestDTO) {
        return boardService.getList(pageRequestDTO);
    }
}

