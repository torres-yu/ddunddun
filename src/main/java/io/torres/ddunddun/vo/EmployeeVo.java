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

    private Long seq;

    private String userId;

    private String pass;

    private String userName;
}
