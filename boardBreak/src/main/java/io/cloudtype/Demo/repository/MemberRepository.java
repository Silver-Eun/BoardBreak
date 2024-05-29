package io.cloudtype.Demo.repository;

import io.cloudtype.Demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
