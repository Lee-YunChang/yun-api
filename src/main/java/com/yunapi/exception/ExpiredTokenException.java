package com.yunapi.exception;

public class ExpiredTokenException extends RuntimeException {
	private static final long serialVersionUID = 1165398089885290400L;

	public ExpiredTokenException() {
        super("토큰이 만료되었습니다.");
    }
}
