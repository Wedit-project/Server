package com.wedit.weditapp.domain.decisions.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class DecisionRequest {

    @Getter
    public static class DecisionRequestDTO{ // 특정 청첩장의 참석의사 조회 요청 DTO
        @NotBlank(message = "청첩장 아이디는 필수입니다.")
        private final String invitationId;

        public DecisionRequestDTO(String invitationId){
            this.invitationId = invitationId;
        }
    }
}
