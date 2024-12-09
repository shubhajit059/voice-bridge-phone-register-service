package org.hishab.phone.cmd.api.dto;

import lombok.Getter;

@Getter
public enum ErrorResponse {
    AUTHORIZATION_ERROR(401, "Unauthorized", ""),
    VALIDATION_ERROR(422, "Validation Error", ""),
    INTERNAL_ERROR(500, "Internal Server Error", "");

    private final int statusCode;
    private final String errorCode;
    private final String errorMessage;

    ErrorResponse(int statusCode, String errorCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
