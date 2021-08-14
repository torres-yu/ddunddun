package io.torres.practice.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends PlatformException {
	public UnauthorizedException() {
		super(HttpStatus.UNAUTHORIZED, 40100, HttpStatus.UNAUTHORIZED.getReasonPhrase());
	}

	public UnauthorizedException(String message) {
		super(HttpStatus.UNAUTHORIZED, 40100, message);
	}

	public UnauthorizedException(String message, int code) {
		super(HttpStatus.UNAUTHORIZED, code, message);
	}

	public UnauthorizedException(int code, String message, Object object) {
		super(HttpStatus.UNAUTHORIZED, code, message, object);
	}
}
