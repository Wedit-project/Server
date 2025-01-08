package com.wedit.weditapp.domain.shared;

import lombok.Getter;

@Getter
public enum AccountSide {
    GROOM("신랑측"),
    BRIDE("신부측");

    private final String side;

    AccountSide(String side){
        this.side = side;
    }
}
