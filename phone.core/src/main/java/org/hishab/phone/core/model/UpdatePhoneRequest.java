package org.hishab.phone.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePhoneRequest {
    private String friendly_name;
    private String sip_termination_uri;
    private String sip_trunk_username;
    private String sip_trunk_password;
    private String inbound_agent_id;
    private String outbound_agent_id;
}
