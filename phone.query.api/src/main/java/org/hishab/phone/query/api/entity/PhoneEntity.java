package org.hishab.phone.query.api.entity;

import lombok.*;
import org.hishab.phone.core.enums.PhoneStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Document(collection = "phone")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder

public class PhoneEntity implements Serializable {

    @Id
    private String phoneNumberId;
    private String provider;
    private String phone_number;
    private String friendly_name;
    private String sip_termination_uri;
    private String sip_trunk_username;
    private String sip_trunk_password;
    private String inbound_agent_id;
    private String outbound_agent_id;
    private Instant createdAt;
    private Instant modifiedAt;
    private PhoneStatus phoneStatus;

}
