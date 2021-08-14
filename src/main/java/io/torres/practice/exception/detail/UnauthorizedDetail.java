package io.torres.practice.exception.detail;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnauthorizedDetail {
	/**
	 * 로그인 실패
	 */
	LOGIN_FAIL(40101),
	/**
	 * 만료된 토큰
	 */
	EXPIRED_TOKEN(40102),
	/**
	 * 토큰 없음
	 */
	TOKEN_IS_NULL(40103),
	/**
	 * 토큰 오류
	 */
	TOKEN_PARSE_ERROR(40104),
	/**
	 * 리프레시토큰 없음
	 */
	NOT_FOUND_REFRESHTOKEN(40105),
	/**
	 * 리프레시토큰 에러
	 */
	INVALID_REFRESHTOKEN(40106),
	/**
	 * 허용 불가 IP
	 */
	ACCESS_DENIED_IP(40107),
	/**
	 * 법인 준비 상태
	 */
	ACCESS_DENIED_COMPANY_STATUS_READY(40108),
	/**
	 * 제3자 정보 제공 동의 하지 않음
	 */
	ACCESS_DENIED_USER_NOT_APPLIED(40109),
	/**
	 * 입사일이 없는 경우
	 */
	ACCESS_DENIED_NO_JOINDATE(40110),
	/**
	 * 접근 권한이 없는 경우
	 */
	NO_PERMISSION(40111),

	/**
	 * 법인 설정 값이 세팅 되지 않아서 리다이렉트해야함
	 */
	ACCESS_DENIED_REDIRECT_TO_ADMIN(40112),
	/**
	 * 사용하지 않는 사용자인 경우
	 */
	ACCESS_DENIED_NOT_USE_USER(40113),
	/**
	 * 관리자가 아닌 경우
	 */
	ACCESS_DENIED_NOT_MANAGER(40114),
	/**
	 * 법인 OFF 상태
	 */
	ACCESS_DENIED_COMPANY_STATUS_CLOSE(40115),
	/**
	 * 법인 OFF 상태 & MANAGER
	 */
	ACCESS_DENIED_COMPANY_STATUS_CLOSE_AND_MANAGER(40116),
	/**
	 * 서비스 준비중으로 접근 금지
	 */
	ACCESS_DENIED_SERVICE_NOT_READY(40117),

	/**
	 * 동기화 되기전 접속한 경우
	 */
	BEFORE_SYNC(40119),

	/**
	 * 사원 동기화 실패인 경우
	 */
	FAIL_EMPLOYEE_SYNC(40120),

	/**
	 * 서버날짜와 로컬날짜가 다른 경우
	 */
	DIFF_SERVER_CLIENT(40121);

	private int code;

	/**
	 * Return the integer value of this status code.
	 */
	public int value() {
		return this.code;
	}
}
