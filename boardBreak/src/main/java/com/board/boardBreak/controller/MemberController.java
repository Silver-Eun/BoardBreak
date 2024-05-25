package com.board.boardBreak.controller;

import com.board.boardBreak.dto.MemberForm;
import com.board.boardBreak.entity.Member;
import com.board.boardBreak.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

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
}
