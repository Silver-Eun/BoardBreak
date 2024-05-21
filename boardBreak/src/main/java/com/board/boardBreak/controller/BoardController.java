package com.board.boardBreak.controller;

import com.board.boardBreak.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;
}
