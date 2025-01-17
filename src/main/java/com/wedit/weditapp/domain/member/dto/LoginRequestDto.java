package com.wedit.weditapp.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {
    private String email;

    @Builder
    public LoginRequestDto(String email) {
        this.email = email;
    }
}
