package org.hishab.phone.query.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hishab.phone.core.dto.Response;
import org.hishab.phone.query.api.entity.PhoneEntity;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneResponse {
    private String phoneNumberId;
    private String phoneNumber;
    private String friendlyName;
    private String sipTerminationUri;
    private String sipTrunkUsername;
    private String sipTrunkPassword;
    private String inboundAgentId;
    private String outboundAgentId;
}
