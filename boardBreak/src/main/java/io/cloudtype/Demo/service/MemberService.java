package io.cloudtype.Demo.service;

import io.cloudtype.Demo.entity.Member;
import io.cloudtype.Demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member selectOne(String id) {
        Optional<Member> result = memberRepository.findById(id);
        if ( result.isPresent() ) return result.get();
        else return null;
    }
}
