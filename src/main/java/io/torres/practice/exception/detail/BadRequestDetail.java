package io.torres.practice.exception.detail;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BadRequestDetail {
	/**
	 * 일반적인 BadRequest
	 * 유효하지 않은 요청했습니다.
	 */
	INVALID_PARAMETER(40001),

	/**
	 * 유효하지 않은 값을 요청하여, 요청 결과가 없습니다.
	 */
	EMPTY_DATA(40002),

	/**
	 * 유효하지 않은 사용자입니다.
	 */
	EMPTY_MEMBER(40003),

	/**
	 * 요청한 조회 결과가 너무 많을 것으로 예상됩니다.
	 * 좀 더 구체적인 요청을 제안 바랍니다.
	 */
	REQUEST_RESULT_TOO_LARGE(40050),

	/**
	 * Java 또는 Spring level 의
	 * 유효성 검사를 통과하지 못했습니다.
	 */
	DEFAULT_INVALID_PARAMETER(40099);

	private int code;

	/**
	 * Return the integer value of this status code.
	 */
	public int value() {
		return this.code;
	}
}
