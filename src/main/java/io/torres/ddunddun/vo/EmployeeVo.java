package io.torres.ddunddun.vo;

import io.torres.ddunddun.code.YesOrNoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeVo {

    private Long cmpId;

    private Long empId;

    private Long deptId;

    private Long userId;

    private String dutyCodeId;

    private String name;

    private String accountId;

    private LocalTime workStartTime;

    private LocalTime workEndTime;

    private Long typeCodeId;

    private String typeCode;

    private String email;

    private String mobileTelephoneNumber;

    private String empStatus;

    private String officeLocation;

    private Long groupNo;

    private String profileImageUrl;

    private LocalDate groupApplyDate;

    private Long groupApplyEmpNo;

    private Long settingSeq;

    private LocalDate joinDate;

    private LocalDate retirementDate;

    private LocalDate layoffDate;

    private LocalDate reinstatementDate;

    private Long layOffReasonCodeId;

    private LocalDate layoffFromDate;

    private LocalDate layoffToDate;

    private LocalDate leaderIssuedDate;

    private LocalDate leaderReleaseDate;

    private LocalDate traineeEndDate;

    private YesOrNoEnum useServiceYn;

    private String localeCode;

    private LocalDateTime renewalAt;

    private String conversationId;
}
