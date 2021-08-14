package io.torres.practice.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode implements CodeInfo {
    SUCCESS("성공"),
    FAIL("실패");

    private String description;
}
