package com.yunapi.exception;

public class InvalidTokenException extends RuntimeException {
	private static final long serialVersionUID = 1165398089885290400L;

	public InvalidTokenException() {
        super("유효하지 않은 토큰입니다.");
    }
}
