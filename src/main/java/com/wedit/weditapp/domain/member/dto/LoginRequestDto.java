package com.wedit.weditapp.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto { // 임시 로그인Dto - 나중에 수정해야 함
    private String email;
    private String password;
}
