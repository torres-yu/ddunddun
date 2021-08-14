package io.torres.practice.ampq.pubsub;

import io.torres.practice.code.CodeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HrEventType implements CodeInfo {

    COMPANY("법인정보"),
    DEPARTMENT("조직도 데이터"),
    USER("임직원"),
    MANAGER("관리자"),
    SECURE("보안설정"),
    HOLIDAY("휴일설정"),
    CODE("코드설정"),
    JOIN_DATE_CHANGED("입사일자 변경"),
    APPROVAL_CHANGED("결재자 변경"),
    NO_JOIN_DATE("입사일자 없음"),
    NO_APPROVAL("부서 결재자 없읍"),
    COMPANY_CREATED("법인생성");

    private String description;
}
