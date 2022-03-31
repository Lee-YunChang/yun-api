package com.yunapi.exception;

public class InvalidInputException extends RuntimeException {
	private static final long serialVersionUID = -2832108568693227235L;

	public InvalidInputException() {
        super("필수 입력값을 잘못 입력하였습니다.");
    }
	
	public InvalidInputException(String msg) {
        super(msg);
    }
	
}