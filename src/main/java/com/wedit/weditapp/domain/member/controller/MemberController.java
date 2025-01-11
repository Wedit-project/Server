package com.wedit.weditapp.domain.member.controller;

import com.wedit.weditapp.domain.member.domain.Member;
import com.wedit.weditapp.domain.member.dto.LoginRequestDto;
import com.wedit.weditapp.domain.member.dto.MemberRequestDto;
import com.wedit.weditapp.domain.member.dto.MemberResponseDto;
import com.wedit.weditapp.domain.member.service.MemberService;
import com.wedit.weditapp.global.response.GlobalResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 가입 API
    @Operation(summary = "회원가입", description = "새로운 유저를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원 가입 성공"),
            @ApiResponse(responseCode = "409", description = "이미 가입된 이메일입니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @PostMapping("/signup")
    public ResponseEntity<GlobalResponseDto<MemberResponseDto>> createMember(@Valid @RequestBody MemberRequestDto requestDto) {
        Member saved = memberService.createMember(requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GlobalResponseDto.success(MemberResponseDto.from(saved), 201));
    }

    // 임시 로그인 API
    @Operation(summary = "임시 로그인", description = "해당 서비스에 로그인합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "로그인 성공"),
            @ApiResponse(responseCode = "401", description = "로그인에 실패하였습니다."),
            @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @PostMapping("/login")
    public ResponseEntity<GlobalResponseDto<String>> login(@RequestBody LoginRequestDto loginRequest) {
        String message = memberService.login(loginRequest);
        return ResponseEntity.ok(GlobalResponseDto.success(message));
    }

    // 모든 회원 조회 API
    @Operation(summary = "모든 회원 조회", description = "등록된 모든 회원의 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping
    public ResponseEntity<GlobalResponseDto<List<MemberResponseDto>>> findAllMembers() {
        List<Member> members = memberService.findAllMembers();
        List<MemberResponseDto> memberResponses = members.stream()
                .map(MemberResponseDto::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(GlobalResponseDto.success(memberResponses));
    }

    // 단일 회원 조회 API
    @Operation(summary = "단일 회원 조회", description = "해당 id를 가진 회원의 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<GlobalResponseDto<MemberResponseDto>> findMemberById(@PathVariable Long userId) {
        Member member = memberService.findMemberById(userId);
        return ResponseEntity.ok(GlobalResponseDto.success(MemberResponseDto.from(member)));
    }
}