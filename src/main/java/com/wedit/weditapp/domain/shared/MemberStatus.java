package com.wedit.weditapp.domain.shared;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MemberStatus {
    ACTIVE("활성화"),
    INACTIVE("비활성화");

    private final String statusType;

    MemberStatus(String statusType) {
        this.statusType = statusType;
    }
}
