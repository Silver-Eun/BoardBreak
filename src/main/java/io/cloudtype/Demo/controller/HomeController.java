package io.cloudtype.Demo.controller;

import io.cloudtype.Demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private BoardService boardService;

    @Autowired
    public void setBoardService(BoardService boardService) {

        this.boardService = boardService;
    }

    @GetMapping("/")
    public String getAllPost(Model model) {
        model.addAttribute("boards", boardService.findAll());
        return "index";
    }

    @GetMapping("/search")
    public String search() {

        return "index";
    }
}
