package org.hishab.phone.core.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hishab.phone.core.model.PhoneRequest;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRegisterEvent {
    private String phoneNumberId;
    private String provider;
    private String phone_number;
    private String friendly_name;
    private String sip_termination_uri;
    private String sip_trunk_username;
    private String sip_trunk_password;
    private String inbound_agent_id;
    private String outbound_agent_id;
}
