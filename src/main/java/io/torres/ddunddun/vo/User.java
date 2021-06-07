package io.torres.ddunddun.vo;

import io.torres.ddunddun.code.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    private Long cmpId;
    private Long empId;
    private Long deptId;
    private Authority authority;
    private String accountId;
    private String localeCode;
}
