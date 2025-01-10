package com.wedit.weditapp.domain.member.controller;

import com.wedit.weditapp.domain.member.domain.Member;
import com.wedit.weditapp.domain.member.domain.repository.MemberRepository;
import com.wedit.weditapp.domain.member.dto.LoginRequestDto;
import com.wedit.weditapp.domain.member.dto.MemberRequestDto;
import com.wedit.weditapp.domain.member.dto.MemberResponseDto;
import com.wedit.weditapp.domain.shared.MemberRole;
import com.wedit.weditapp.domain.shared.MemberStatus;
import com.wedit.weditapp.global.error.ErrorCode;
import com.wedit.weditapp.global.error.exception.CommonException;
import com.wedit.weditapp.global.response.GlobalResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

}


