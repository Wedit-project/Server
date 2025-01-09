package com.wedit.weditapp.domain.decisions.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class DecisionResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DecisionResponseDTO{
        private Long decisionId;
        private String name;
        private String phoneNumber;
        private Integer addPerson;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DecisionResponseDTOList{ // 특정 청첩장의 참석의사 조회 응답 DTO
        private List<DecisionResponse.DecisionResponseDTO> decisionList;
    }
}