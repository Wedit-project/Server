package com.wedit.weditapp.domain.member.dto;

import com.wedit.weditapp.domain.member.domain.Member;
import com.wedit.weditapp.domain.shared.MemberRole;
import com.wedit.weditapp.domain.shared.MemberStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String email;
    private String name;
    private MemberRole role;
    private MemberStatus status;

    @Builder
    public static MemberResponseDto from(Member member) {
        MemberResponseDto responseDto = new MemberResponseDto();
        responseDto.id = member.getId();
        responseDto.email = member.getEmail();
        responseDto.name = member.getName();
        responseDto.role = member.getRole();
        responseDto.status = member.getStatus();
        return responseDto;
    }
}

