package io.torres.ddunddun.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoResultResponse<T> {
    private int status;
    private int code;
    private String message;
    private T result;

    public LoResultResponse(T result) {
        this.status = HttpStatus.OK.value();
        this.message = "success";
        this.result = result;
    }

    public LoResultResponse(HttpStatus httpStatus) {
        this.status = httpStatus.value();
    }
}
