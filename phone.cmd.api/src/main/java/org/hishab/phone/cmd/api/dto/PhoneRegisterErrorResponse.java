package org.hishab.phone.cmd.api.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hishab.phone.core.dto.BaseResponse;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonPropertyOrder({"status", "message", "error"})
public class PhoneRegisterErrorResponse extends BaseResponse {
    private String status;
    private String message;
    private String error;

    public PhoneRegisterErrorResponse(String status, String message, String error) {
        super(message);
        this.status = status;
        this.message = message;
        this.error = error;
    }
}
