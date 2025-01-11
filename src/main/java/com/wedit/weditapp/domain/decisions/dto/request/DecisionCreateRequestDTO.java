package com.wedit.weditapp.domain.decisions.dto.request;

import com.wedit.weditapp.domain.decisions.domain.Decisions;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DecisionCreateRequestDTO {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @NotBlank(message = "전화번호는 필수입니다.")
    private String phoneNumber;

    @NotNull(message = "추가 인원은 필수입니다.")
    private Integer addPerson;
    
}
