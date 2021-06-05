package io.torres.ddunddun.code;

import lombok.Getter;

public enum YesOrNoEnum {

    Y("Y"),N("N");

    @Getter
    final private String code;

    YesOrNoEnum(String code) {
        this.code = code;
    }
}
