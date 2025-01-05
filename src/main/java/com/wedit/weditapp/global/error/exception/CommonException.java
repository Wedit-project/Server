package com.wedit.weditapp.global.error.exception;

import com.wedit.weditapp.global.error.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonException extends RuntimeException {
	private final ErrorCode errorCode;
}
