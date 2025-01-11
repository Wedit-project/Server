package com.wedit.weditapp.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDto {
    @Email(message = "이메일 형식을 지켜야 합니다.")
    @NotBlank(message = "이메일을 반드시 입력해야 합니다.")
    private String email;

    @NotBlank(message = "비밀번호를 반드시 입력해야 합니다.")
    private String password; // 임시 비밀번호

    @NotBlank(message = "이름을 반드시 입력해야 합니다.")
    private String name;
}
