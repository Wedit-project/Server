package com.wedit.weditapp.domain.shared;

import lombok.Getter;

@Getter
public enum MemberRole {
    USER("사용자"),
    ADMIN("관리자"),
    GUEST("게스트");

    private final String roleType;

    MemberRole(String roleType) {
        this.roleType = roleType;
    }
}
