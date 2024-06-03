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

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class BoardController {
    private BoardRepository boardRepository;

    @Autowired
    public void setBoardRepository(BoardRepository boardRepository) {

        this.boardRepository = boardRepository;
    }

    private BoardService boardService;

    @Autowired
    public void setBoardService(BoardService boardService) {

        this.boardService = boardService;
    }

    // 게시글 쓰기
    @GetMapping("/board/new")
    public String newBoardForm() {
        return "new";
    }

    @PostMapping("/board/create")
    public String createBoard(BoardForm form) {
        // 1. DTO를 변환 : Entity
        Board board = form.toEntity();
        // 2. Repository에서 Entity를 DB 안에 저장
        boardRepository.save(board);
        return "redirect:/";
    }

    // 게시글 삭제하기
    @GetMapping("/board/delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        boardRepository.deleteById(id);
        return "redirect:/";
    }

    // 게시글 상세보기
    @GetMapping("/board/{id}")
    public String detailBoard(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.selectOne(id));
        return "boardDetail";
    }

    // 게시글 수정하기
    @GetMapping("/board/edit/{id}")
    public String editBoard(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.selectOne(id));
        return "boardUpdate";
    }

    @PostMapping("/board/update/{id}")
    public String updateBoard(@PathVariable Long id, BoardForm form, Model model) {
        // 기존 게시글 가져오기
        Board board = boardService.selectOne(id);

        if (board != null) {
            // 수정할 필드 업데이트
            board.setTitle(form.getTitle());
            board.setContent(form.getContent());
            board.setAuthor(form.getAuthor());
            // 저장
            boardRepository.save(board);
        }

        model.addAttribute("apple", form);
        return "redirect:/board/" + id;
    }
}
