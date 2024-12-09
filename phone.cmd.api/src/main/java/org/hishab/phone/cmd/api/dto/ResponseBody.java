package org.hishab.phone.cmd.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Builder
@Data
public class ResponseBody {
    @JsonProperty("phone_number")
    private String phone_number;

    @JsonProperty("friendly_name")
    private String friendly_name;

    @JsonProperty("sip_termination_uri")
    private String sip_termination_uri;

    @JsonProperty("sip_trunk_username")
    private String sip_trunk_username;

    @JsonProperty("sip_trunk_password")
    private String sip_trunk_password;

    @JsonProperty("inbound_agent_id")
    private String inbound_agent_id;

    @JsonProperty("outbound_agent_id")
    private String outbound_agent_id;

}

