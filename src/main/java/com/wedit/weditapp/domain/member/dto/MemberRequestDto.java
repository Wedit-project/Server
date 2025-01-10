package com.wedit.weditapp.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDto {
    private String email;
    private String password; // 임시 비밀번호
    private String name;
}
