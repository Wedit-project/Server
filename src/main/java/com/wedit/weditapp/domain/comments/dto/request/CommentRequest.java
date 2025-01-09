package com.wedit.weditapp.domain.comments.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class CommentRequest {

    @Getter
    public static class CommentRequestDTO{ // 특정 청첩장의 방명록 조회 요청 DTO
        @NotBlank(message = "청첩장 아이디는 필수입니다.")
        private final String invitationId;

        public CommentRequestDTO(String invitationId){
            this.invitationId = invitationId;
        }
    }
}
