package org.hishab.phone.cmd.api.exception;

import org.hishab.phone.cmd.api.dto.ErrorResponse;

public class PhoneException extends BaseException {

    public PhoneException(ErrorResponse errorResponse, String message, Throwable throwable) {
        super(errorResponse, message, throwable);
    }

    public PhoneException(ErrorResponse errorResponse, String message) {
        super(errorResponse, message);
    }
}