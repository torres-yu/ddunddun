package io.torres.practice.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeVo {

    private Long seq;

    private String userId;
    private String pass;
    private String userName;
}
