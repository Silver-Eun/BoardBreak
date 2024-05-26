package com.board.boardBreak.controller;

import com.board.boardBreak.dto.MemberForm;
import com.board.boardBreak.entity.Member;
import com.board.boardBreak.repository.MemberRepository;
import com.board.boardBreak.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService service;

    // 회원가입
    @GetMapping("/member/new")
    public String newMemberForm() {
        return "join";
    }

    @PostMapping("/member/join")
    public String newMember(MemberForm form) {
        log.info(form.toString());

        // 1. DTO를 변환 : Entity
        Member member = form.toEntity();
        log.info("Entity -> " + member.toString());
        // 2. Repository에서 Entity를 DB 안에 저장
        Member saved = memberRepository.save(member);
        log.info("saved -> " + saved.toString());
        return "redirect:/home";
    }

    // 로그인
    @GetMapping(value="/loginForm")
    public String loginForm() {
        return "/login";
    }

    // => Login 처리 : Post
    @PostMapping(value="/login/new")
    public String login(HttpSession session, Member member) {
        // ** 로그인 Service 처리
        // 1. 요청분석
        String password = member.getPassword();
        String uri = "redirect:/home";

        // 2. 서비스 처리
        member = service.selectOne(member.getId());

        if (member != null) {
            session.setAttribute("loginID", member.getId());
            session.setAttribute("loginName", member.getName());
        } else {
            uri = "/login";
        }
        return uri;
    }
}
