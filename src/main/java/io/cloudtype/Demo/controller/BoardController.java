package io.cloudtype.Demo.controller;

import io.cloudtype.Demo.dto.BoardForm;
import io.cloudtype.Demo.dto.CommentForm;
import io.cloudtype.Demo.entity.Board;
import io.cloudtype.Demo.repository.BoardRepository;
import io.cloudtype.Demo.service.BoardService;
import io.cloudtype.Demo.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@Slf4j
public class BoardController {

    // 레포지토리 및 서비스 주입
    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardRepository boardRepository, BoardService boardService) {
        this.boardRepository = boardRepository;
        this.boardService = boardService;
    }

    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService){

        this.commentService = commentService;
    }

    // 게시글 쓰기
    @GetMapping("/board/new")
    public String newBoardForm(HttpSession session, HttpServletResponse response) throws IOException {
        // 로그인해야 게시글 작성 가능
        if(session.getAttribute("loginID") == null) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script> alert('로그인해주세요.');");
            out.println("history.go(-1); </script>");
            out.close();
        }
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
//        boardRepository.deleteById(id);
        boardService.deleteBoardWithComments(id);
        return "redirect:/";
    }

    // 게시글 상세보기
    @GetMapping("/board/{id}")
    public String detailBoard(@PathVariable Long id, Model model) {
        // 데이터 가져오기
        Board board = boardService.selectOne(id);
        List<CommentForm> commentList = commentService.comments(id);

        // Date를 LocalDateTime으로 변환
        LocalDateTime localDateTime = LocalDateTime.ofInstant(board.getCreatedAt().toInstant(), ZoneId.systemDefault());

        // 한국 시간대로 변환
        LocalDateTime seoulDateTime = localDateTime.atZone(ZoneId.systemDefault())
                .withZoneSameInstant(ZoneId.of("Asia/Seoul"))
                .toLocalDateTime();

        // 문자열로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String seoulDateTimeString = seoulDateTime.format(formatter);

        // 모델에 데이터 추가
        model.addAttribute("board", board);
        model.addAttribute("comment", commentList);

        // 변환된 날짜를 추가
        model.addAttribute("createdAt", seoulDateTimeString);

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

    // 게시글 검색
    @GetMapping("/board/search")
    public String searchBoards(
            @RequestParam("keyword") String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        // 페이지네이션과 키워드를 함께 사용하여 검색
        Page<Board> boardPage = boardRepository.searchByKeyword(keyword, pageable);

        model.addAttribute("boardPage", boardPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", boardPage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("keyword", keyword); // 검색 키워드 추가

        return "index"; // 검색 결과를 보여줄 페이지
    }

    @GetMapping("/api/board/search")
    public ResponseEntity<Page<Board>> searchBoardsApi(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        // 레포지토리의 메소드 호출
        Page<Board> boardPage = boardRepository.searchByKeyword(keyword, pageable);

        return ResponseEntity.ok(boardPage);
    }
}
