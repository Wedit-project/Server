package com.wedit.weditapp.global.auth.login.handler;

import com.wedit.weditapp.domain.member.domain.Member;
import com.wedit.weditapp.domain.member.domain.repository.MemberRepository;
import com.wedit.weditapp.global.auth.login.domain.CustomOAuth2User;
import com.wedit.weditapp.global.auth.jwt.JwtProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Login 성공!");

        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getEmail();

        // DB에서 사용자 조회
        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        if (optionalMember.isEmpty()) {
            // 새로운 사용자인 경우 추가 정보 입력 페이지로 리다이렉트
            handleNewUser(response, oAuth2User);
        } else {
            // 기존 사용자 로그인 성공
            handleExistingUser(response, optionalMember.get());
        }
    }

    // 새로운 사용자 처리 - 추가 정보 입력 페이지로 리다이렉트
    private void handleNewUser(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {
        String accessToken = jwtProvider.createAccessToken(oAuth2User.getEmail());
        response.addHeader(jwtProvider.getAccessHeader(), "Bearer " + accessToken);

        log.info("새로운 사용자: 추가 정보 입력 페이지로 리다이렉트");
        response.sendRedirect("/oauth2/sign-up"); // 프론트의 회원가입 추가 정보 입력 폼으로 리다이렉트
    }

    // 기존 사용자 처리 - 토큰 발급 및 로그인 성공 처리
    private void handleExistingUser(HttpServletResponse response, Member member) throws IOException {
        log.info("기존 사용자 로그인 성공: {}", member.getEmail());

        String accessToken = jwtProvider.createAccessToken(member.getEmail());
        String refreshToken = member.getRefreshToken();

        if (refreshToken == null || !jwtProvider.validateToken(refreshToken)) {
            // Refresh Token이 없거나 유효하지 않은 경우 새로 발급
            refreshToken = jwtProvider.createRefreshToken();
            member.updateRefreshToken(refreshToken);
            memberRepository.save(member);
            log.info("새로운 Refresh Token 발급: {}", refreshToken);
        }

        jwtProvider.sendAccessAndRefreshToken(response, accessToken, refreshToken);
    }
}
