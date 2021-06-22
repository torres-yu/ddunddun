package io.torres.ddunddun.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberVo {

    private Long id;
    private String password;
    private String userName;
}
