package com.wedit.weditapp.domain.comments.dto.request;

import com.wedit.weditapp.domain.comments.domain.Comments;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateRequestDTO {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;


}
