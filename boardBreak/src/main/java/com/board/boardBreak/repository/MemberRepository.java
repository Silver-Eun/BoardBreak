package com.board.boardBreak.repository;

import com.board.boardBreak.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
