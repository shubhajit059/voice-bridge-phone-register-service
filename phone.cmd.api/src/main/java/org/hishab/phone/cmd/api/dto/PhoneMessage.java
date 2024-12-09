package org.hishab.phone.cmd.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PhoneMessage {
    @JsonProperty("role")
    private String role;

    @JsonProperty("content")
    private String content;
}
