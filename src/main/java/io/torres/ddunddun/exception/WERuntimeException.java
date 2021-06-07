package io.torres.ddunddun.exception;

import io.torres.ddunddun.result.ResultResponse;
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
