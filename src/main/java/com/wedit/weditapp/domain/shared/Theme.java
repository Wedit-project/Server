package com.wedit.weditapp.domain.shared;

import lombok.Getter;

@Getter
public enum Theme {
    TRADITIONAL("전통형"),
    BASIC("일반형");

    private final String themeType;

    Theme(String themeType) {
        this.themeType = themeType;
    }
}
