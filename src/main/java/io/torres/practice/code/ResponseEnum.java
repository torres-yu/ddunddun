package io.torres.practice.code;

import lombok.Getter;

@Getter
public enum ResponseEnum {

    Code("code"),
    Message("message");

    private String code;

    ResponseEnum(String code) {
        this.code = code;
    }
}
