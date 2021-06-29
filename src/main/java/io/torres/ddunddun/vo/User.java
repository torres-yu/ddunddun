package io.torres.ddunddun.vo;

import io.torres.ddunddun.code.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    private Long seq;
    private String emaiId;
    private String password;
    private String username;
    private List<String> roles;
}
