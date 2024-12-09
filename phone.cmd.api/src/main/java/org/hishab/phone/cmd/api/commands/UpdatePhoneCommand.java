package org.hishab.phone.cmd.api.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
@AllArgsConstructor
public class UpdatePhoneCommand {
    @TargetAggregateIdentifier
    private final String phoneNumberId;
    private String friendly_name;
    private String sip_termination_uri;
    private String sip_trunk_username;
    private String sip_trunk_password;
    private String inbound_agent_id;
    private String outbound_agent_id;
}
