package org.hishab.phone.cmd.api.exception;

import org.hishab.phone.cmd.api.dto.ErrorResponse;
import lombok.Getter;

@Getter
public class BaseException extends Exception {

    private final ErrorResponse errorResponse;

    private final String message;

    public BaseException(ErrorResponse errorResponse, String message, Throwable cause) {
        super(errorResponse.getErrorMessage(), cause);
        this.errorResponse = errorResponse;
        this.message = message;
    }

    public BaseException(ErrorResponse errorResponse, String message) {
        this.errorResponse = errorResponse;
        this.message = message;
    }
}
