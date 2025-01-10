package com.wedit.weditapp.domain.decisions.dto.response;

import com.wedit.weditapp.domain.decisions.domain.Decisions;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DecisionResponseDTO {
    private Long decisionId;
    private String name;
    private String phoneNumber;
    private Integer addPerson;

    @Builder
    private DecisionResponseDTO(Long decisionId, String name, String phoneNumber, Integer addPerson) {
        this.decisionId = decisionId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addPerson = addPerson;
    }

    public static DecisionResponseDTO from(Decisions decision) {
        return DecisionResponseDTO.builder()
                .decisionId(decision.getId())
                .name(decision.getName())
                .phoneNumber(decision.getPhoneNumber())
                .addPerson(decision.getAddPerson())
                .build();
    }
}