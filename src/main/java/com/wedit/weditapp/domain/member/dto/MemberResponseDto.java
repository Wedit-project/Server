package com.wedit.weditapp.domain.member.dto;

import com.wedit.weditapp.domain.member.domain.Member;
import com.wedit.weditapp.domain.shared.MemberRole;
import com.wedit.weditapp.domain.shared.MemberStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponseDto {
    private Long id;
    private String email;
    private String name;
    private MemberRole role;
    private MemberStatus status;

    public static MemberResponseDto from(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .role(member.getRole())
                .status(member.getStatus())
                .build();
    }
}

