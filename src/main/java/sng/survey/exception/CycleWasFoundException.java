package sng.survey.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CycleWasFoundException extends RuntimeException {
    public CycleWasFoundException(String message) {
        super(message);
    }
}
