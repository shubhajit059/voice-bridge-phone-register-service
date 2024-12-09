package org.hishab.phone.cmd.api.exception;

import org.hishab.phone.cmd.api.dto.ErrorResponse;
import org.hishab.phone.cmd.api.dto.PhoneRegisterErrorResponse;
import org.hishab.phone.cmd.api.dto.PhoneRegisteredResponse;
import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CommandExecutionException.class)
    public ResponseEntity<PhoneRegisterErrorResponse> handleResourceNotFoundException(CommandExecutionException ex) {
        return new ResponseEntity<>(PhoneRegisterErrorResponse
                .builder()
                .status(ErrorResponse.INTERNAL_ERROR.getErrorCode())
                .message(ex.getMessage())
                .error(ex.getCause().getMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PhoneException.class)
    public ResponseEntity<PhoneRegisterErrorResponse> handleCallException(PhoneException ex) {
        return new ResponseEntity<>(PhoneRegisterErrorResponse
                .builder()
                .status(ex.getErrorResponse().getErrorCode())
                .message(ex.getMessage())
                .error(ex.getCause().getMessage())
                .build(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<PhoneRegisterErrorResponse> handleGlobalException(Exception ex) {
        return new ResponseEntity<>(PhoneRegisterErrorResponse
                .builder()
                .status(ErrorResponse.INTERNAL_ERROR.getErrorCode())
                .message(ex.getMessage())
                .error(ex.getCause().getMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
