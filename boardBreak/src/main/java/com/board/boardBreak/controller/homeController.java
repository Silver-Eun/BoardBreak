package com.board.boardBreak.controller;

import com.board.boardBreak.entity.Board;
import com.board.boardBreak.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/home")
    public String getAllPost(Model model) {
        model.addAttribute("boards", boardService.findAll());
        return "/home";
    }
}
