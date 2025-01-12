package com.wedit.weditapp.domain.member.domain.repository;

import com.wedit.weditapp.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 임시 로그인용 : 이메일로 회원 찾기
    Optional<Member> findByEmail(String email);
}
