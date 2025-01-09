package com.wedit.weditapp.domain.comments.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class CommentResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentResponseDTO{
        private Long commentId;
        private String name;
        private String content;
    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentResponseDTOList{ //특정 청첩장의 방명록 조회 응답 DTO
        private List<CommentResponse.CommentResponseDTO> commentList;
    }
}
