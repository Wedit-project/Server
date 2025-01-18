package com.wedit.weditapp.domain.member.service;

import com.wedit.weditapp.domain.member.domain.Member;
import com.wedit.weditapp.domain.member.domain.repository.MemberRepository;
import com.wedit.weditapp.domain.member.dto.LoginRequestDto;
import com.wedit.weditapp.domain.member.dto.MemberRequestDto;
import com.wedit.weditapp.domain.member.dto.MemberResponseDto;
import com.wedit.weditapp.domain.member.dto.TokenResponseDto;
import com.wedit.weditapp.global.error.ErrorCode;
import com.wedit.weditapp.global.error.exception.CommonException;
import com.wedit.weditapp.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider; // JWT 발급을 위해

    // [회원가입 관련] - 이미 존재하는 이메일인지 검사 + Member 엔티티 생성 + DB저장 및 반환
    public Member createMember(MemberRequestDto requestDto) {
        if (memberRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new CommonException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        Member member = Member.createUser(
                requestDto.getEmail(),
                requestDto.getName()
        );

        return memberRepository.save(member);
    }

    // [로그인 관련] - 이메일로 회원 조회 + AccessToken, RefreshToken 생성 + RefreshToken을 DB에 저장 + 반환
    public TokenResponseDto login(LoginRequestDto loginRequest) {
        Member member = memberRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new CommonException(ErrorCode.USER_NOT_FOUND));

        // AccessToken, RefreshToken 생성
        String accessToken = jwtProvider.createAccessToken(member.getEmail());
        String refreshToken = jwtProvider.createRefreshToken();

        // DB에 RefreshToken 저장
        member.updateRefreshToken(refreshToken);
        memberRepository.save(member);

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // [모든 회원 조회]
    public List<MemberResponseDto> findAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(MemberResponseDto::from)
                .collect(Collectors.toList());
    }

    // [단일 회원 조회]
    public MemberResponseDto findMember(Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.USER_NOT_FOUND));
        return MemberResponseDto.from(member);
    }
}
