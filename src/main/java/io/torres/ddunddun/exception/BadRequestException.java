package io.torres.ddunddun.exception;

import io.torres.ddunddun.exception.detail.BadRequestDetail;
import org.springframework.http.HttpStatus;


public class BadRequestException extends PlatformException {

	public BadRequestException() {
		super(HttpStatus.BAD_REQUEST, BadRequestDetail.INVALID_PARAMETER.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
	}

	public BadRequestException(String message) {
		super(HttpStatus.BAD_REQUEST, BadRequestDetail.INVALID_PARAMETER.value(), message);
	}

	public BadRequestException(String message, int code) {
		super(HttpStatus.BAD_REQUEST, code, message);
	}

	public BadRequestException(String message, BadRequestDetail detail) {
		super(HttpStatus.BAD_REQUEST, detail.value(), message);
	}

	public BadRequestException(String message, int code, Object error) {
		super(HttpStatus.BAD_REQUEST, code, message, error);
	}
}
