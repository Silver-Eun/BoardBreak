package io.cloudtype.Demo.controller;

import io.cloudtype.Demo.dto.BoardForm;
import io.cloudtype.Demo.dto.PageRequestDTO;
import io.cloudtype.Demo.dto.PageResultDTO;
import io.cloudtype.Demo.entity.Board;
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

    // 메인페이지
    @GetMapping("/")
    public String getAllPost(PageRequestDTO pageRequestDTO, Model model) {
        // model.addAttribute("boards", boardService.findAll());
        // 페이지네이션된 게시글 목록 가져오기
        PageResultDTO<BoardForm, Board> result = boardService.getList(pageRequestDTO);
        model.addAttribute("result", result);
        return "index";
    }

    // 검색
    @GetMapping("/search")
    public String search() {

        return "index";
    }
}
