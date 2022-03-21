package com.yunapi.exception;

public class AuthorizationHeaderNotExistsException extends RuntimeException {
	private static final long serialVersionUID = -7285236784084776913L;

	public AuthorizationHeaderNotExistsException() {
        super("Authorization 헤더가 없습니다.");
    }
}