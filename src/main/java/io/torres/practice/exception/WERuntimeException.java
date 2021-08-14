package io.torres.practice.exception;

import io.torres.practice.result.ResultResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WERuntimeException extends RuntimeException {
    protected ResultResponse resultResponse;

    public WERuntimeException(ResultResponse resultResponse) {
        this.resultResponse = resultResponse;
    }

    public WERuntimeException(String errorMessage) {
        super(errorMessage);
        this.resultResponse = new ResultResponse("RC005",errorMessage);
    }
}
