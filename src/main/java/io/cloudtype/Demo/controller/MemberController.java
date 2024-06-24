package io.cloudtype.Demo.controller;

import io.cloudtype.Demo.dto.MemberForm;
import io.cloudtype.Demo.entity.Member;
import io.cloudtype.Demo.repository.MemberRepository;
import io.cloudtype.Demo.service.MemberService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
@Slf4j
public class MemberController {
    private MemberRepository memberRepository;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {

        this.memberService = memberService;
    }

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
        // 2. Repository에서 Entity를 DB 안에 저장
        memberRepository.save(member);
        return "redirect:/";
    }

    // 로그인
    @GetMapping(value = "/loginForm")
    public String loginForm() {
        return "login";
    }

    @PostMapping(value = "/login/new")
    public String login(HttpSession session, @RequestParam String id,
                        @RequestParam String password, Member member,
                        HttpServletResponse response) throws IOException {

        member = memberService.selectOne(member.getId());

        if (member != null) {
            String dbPassword = member.getPassword();
            String dbId = member.getId();

            if (password.equals(dbPassword) && id.equals(dbId)) {
                session.setAttribute("loginID", member.getId());
                session.setAttribute("loginName", member.getName());
                return "redirect:/";
            }
        }

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("alert('아이디 또는 비밀번호가 틀립니다.');");
        out.println("history.go(-1);");
        out.println("</script>");
        out.close();

        return null;
    }

    // 로그아웃
    @GetMapping(value = "/logout")
    public String logout(HttpSession session) {

        session.invalidate();
        return "redirect:/";
    }
}
