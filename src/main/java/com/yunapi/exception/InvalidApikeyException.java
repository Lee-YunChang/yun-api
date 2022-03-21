package com.yunapi.exception;

public class InvalidApikeyException extends RuntimeException {
	private static final long serialVersionUID = 1165398089885290400L;

	public InvalidApikeyException() {
        super("유효하지 않은 apikey입니다.");
    }
}
