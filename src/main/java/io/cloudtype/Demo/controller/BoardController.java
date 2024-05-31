package io.cloudtype.Demo.controller;

import io.cloudtype.Demo.dto.BoardForm;
import io.cloudtype.Demo.entity.Board;
import io.cloudtype.Demo.repository.BoardRepository;
import io.cloudtype.Demo.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@Slf4j
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;

    // 게시글 쓰기
    @GetMapping("/board/new")
    public String newBoardForm() {
        return "new";
    }

    @PostMapping("/board/create")
    public String createBoard(BoardForm form) {
        log.info(form.toString());

        // 1. DTO를 변환 : Entity
        Board board = form.toEntity();
        log.info("Entity -> " + board.toString());
        // 2. Repository에서 Entity를 DB 안에 저장
        Board saved = boardRepository.save(board);
        log.info("saved -> " + saved.toString());
        return "redirect:/";
    }

    // 게시글 삭제하기
    @GetMapping("/board/delete/{id}")
    public String deleteBoard(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boardRepository.deleteById(id);
        return "redirect:/";
    }

    // 게시글 상세보기
    @GetMapping("/board/{id}")
    public String detailBoard(Model model, Board board) {
        model.addAttribute("board", boardService.selectOne(board.getId()));
        return "boardDetail";
    }
}
