package io.torres.practice.ampq.pubsub;

import io.torres.practice.code.CodeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HrEventName implements CodeInfo {
    HR_COMPANY_CREATED("법인 생성 이벤트"),
    HR_ORG_INFO_CHANGED("조직 변경 이벤트"),
    WE_SERVICE_AVAILABLE("법인 상태값 오픈 알림"),
    HR_EMP_JOIN_DATE_INSERT_ALL("입사일 일괄 업로드"),
    HR_EMP_JOIN_DATE_CHANGED("입사일 변경 단건"),
    HR_NOT_EXIST_DEPT_APPROVAL_EMP("결재권자 누락"),
    HR_NOT_EXIST_EMP_JOIN_DATE("입사일 누락"),
    HR_SYNC_COMPLETE("배치 완료"),
    HR_APPROVAL_EMP_CHANGED("결재권자 변경");

    private String description;
}
