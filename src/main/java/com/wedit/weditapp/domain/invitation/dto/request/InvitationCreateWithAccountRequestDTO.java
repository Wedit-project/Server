package com.wedit.weditapp.domain.invitation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InvitationCreateWithAccountRequestDTO {
    @NotBlank(message = "side cannot be blank")
    private String side; // 신랑/신부 구분

    @NotBlank(message = "accountNumber cannot be blank")
    private String accountNumber; // 계좌 번호

    @NotBlank(message = "bankName cannot be blank")
    private String bankName; // 은행 이름

    @NotBlank(message = "accountHolder cannot be blank")
    private String accountHolder; // 예금주 이름
}