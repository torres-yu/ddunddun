package io.torres.practice.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultResponse {

    private String code;
    private String message;

    public ResultResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
