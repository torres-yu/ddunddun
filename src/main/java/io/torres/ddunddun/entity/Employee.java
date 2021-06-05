package io.torres.ddunddun.entity;

import io.torres.ddunddun.code.YesOrNoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Column(name = "cmp_id")
    private Long cmpId;

    @Id
    @Column(name = "emp_id")
    private Long empId;

    @Column(name = "dept_id")
    private Long deptId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "duty_code_id")
    private String dutyCodeId;

    @Column
    private String name;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "work_start_time")
    private LocalTime workStartTime;

    @Column(name = "work_end_time")
    private LocalTime workEndTime;

    @Column(name = "type_code_id")
    private Long typeCodeId;

    @Column(name = "type_code")
    private String typeCode;

    @Column
    private String email;

    @Column(name = "mobile_telephone_number")
    private String mobileTelephoneNumber;

    @Column(name = "emp_status", columnDefinition = "char")
    private String empStatus;

    @Column(name = "office_location")
    private String officeLocation;

    @Column(name = "group_no")
    private Long groupNo;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "group_apply_date")
    private LocalDate groupApplyDate;

    @Column(name = "group_apply_emp_no")
    private Long groupApplyEmpNo;

    @Column(name = "setting_seq")
    private Long settingSeq;

    @Column(name = "join_date")
    private LocalDate joinDate;

    @Column(name = "retirement_date")
    private LocalDate retirementDate;

    @Column(name = "layoff_date")
    private LocalDate layoffDate;

    @Column(name = "reinstatement_date")
    private LocalDate reinstatementDate;

    @Column(name = "layoff_reason_code_id")
    private Long layOffReasonCodeId;

    @Column(name = "layoff_from_date")
    private LocalDate layoffFromDate;

    @Column(name = "layoff_to_date")
    private LocalDate layoffToDate;

    @Column(name = "leader_issued_date")
    private LocalDate leaderIssuedDate;

    @Column(name = "leader_release_date")
    private LocalDate leaderReleaseDate;

    @Column(name = "trainee_end_date")
    private LocalDate traineeEndDate;

    @Column(name = "use_service_yn", columnDefinition = "char")
    @Enumerated(EnumType.STRING)
    private YesOrNoEnum useServiceYn;

    @Column(name = "locale_code")
    private String localeCode;

    @Column(name = "renewal_at")
    private LocalDateTime renewalAt;

    @Column(name = "conversation_id")
    private String conversationId;
}
