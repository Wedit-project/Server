package com.wedit.weditapp.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	//공통
	INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", "서버 내부 에러"),
	VALIDATION_FAILED(400, "VALIDATION_FAILED", "요청 값이 올바르지 않습니다."),
	INVALID_INPUT_VALUE(400, "INVALID_INPUT_VALUE", "유효하지 않은 입력값입니다."),
	INVALID_FILE_FORMAT(400, "INVALID_FILE_FORMAT", "올바르지 않은 파일 형식입니다."),
	INVALID_TOKEN(401, "INVALID_TOKEN",  "유효하지 않은 토큰입니다."),
	REJECT_DUPLICATION(409, "REJECT_DUPLICATION", "중복된 값입니다."),
	EMPTY_FIELD(400, "EMPTY_FIELD", "공백이면 안됩니다."),

	//회원
	LOGIN_FAIL(401, "LOGIN_FAIL", "로그인에 실패하였습니다."),
	USER_NOT_FOUND(404, "USER_NOT_FOUND", "유저를 찾을 수 없습니다."),
	MEMBER_ALREADY_EXISTS(409, "MEMBER_ALREADY_EXISTS", "이미 존재하는 회원입니다."),
	EMAIL_ALREADY_EXISTS(409, "EMAIL_ALREADY_EXISTS", "이미 가입된 이메일입니다."),
	DUPLICATE_NICKNAME(409, "NICKNAME_ALREADY_EXISTS", "이미 존재하는 닉네임입니다."),

	//JWT
	INVALID_JWT_SIGNATURE(401, "INVALID_JWT_SIGNATURE", "잘못된 JWT 서명입니다."),
	EXPIRED_JWT_TOKEN(401, "EXPIRED_JWT_TOKEN", "만료된 JWT 토큰입니다."),
	UNSUPPORTED_JWT_TOKEN(401, "UNSUPPORTED_JWT_TOKEN", "지원되지 않는 JWT 토큰입니다."),
	ILLEGAL_JWT(400, "ILLEGAL_JWT", "JWT 토큰 핸들러 컴팩트 오류입니다."),
	EMAIL_NOT_PROVIDED(400, "EMAIL_NOT_PROVIDED", "이메일이 제공되지 않았습니다."),

	//작업
	DELETE_FORBIDDEN(403, "DELETE_FORBIDDEN", "삭제 권한이 없습니다.");

	private final int status;
	private final String code;
	private final String message;
}
