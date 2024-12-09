package org.hishab.phone.cmd.api.aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.axonframework.modelling.command.AggregateCreationPolicy;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.CreationPolicy;
import org.axonframework.spring.stereotype.Aggregate;
import org.hishab.phone.cmd.api.commands.DeletePhoneCommand;
import org.hishab.phone.cmd.api.commands.RegisterPhoneCommand;
import org.hishab.phone.cmd.api.commands.UpdatePhoneCommand;
import org.hishab.phone.cmd.api.dto.ErrorResponse;
import org.hishab.phone.cmd.api.dto.ResponseBody;
import org.hishab.phone.cmd.api.exception.PhoneException;
import org.hishab.phone.core.events.PhoneDeleteEvent;
import org.hishab.phone.core.events.PhoneRegisterEvent;
import org.hishab.phone.core.events.PhoneUpdateEvent;
import org.hishab.phone.core.model.PhoneRequest;
import org.hishab.phone.core.model.UpdatePhoneRequest;

@Data
@NoArgsConstructor
@Aggregate(snapshotTriggerDefinition = "phoneAggregateSnapshotTriggerDefinition")
@Slf4j
public class PhoneAggregate {

    @AggregateIdentifier
    private String phoneNumberId;
    private String provider;
    private String phone_number;
    private String friendly_name;
    private String sip_termination_uri;
    private String sip_trunk_username;
    private String sip_trunk_password;
    private String inbound_agent_id;
    private String outbound_agent_id;

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    public ResponseBody handle(RegisterPhoneCommand command, MetaData metaData) throws PhoneException {
        String traceId = (String) metaData.getOrDefault("traceId", "No Trace ID");
        System.out.println("Trace ID in command module: " + traceId);
        log.info("Trace ID in command module: {}", traceId);
        log.info("Handling createPhoneCommand for phone number ID {}", command.getPhoneNumberId());

        validateCommand(command);
        AggregateLifecycle.apply(PhoneRegisterEvent.builder()
                .phoneNumberId(command.getPhoneNumberId())
                .provider(command.getProvider())
                .phone_number(command.getPhone_number())
                .friendly_name(command.getFriendly_name())
                .sip_termination_uri(command.getSip_termination_uri())
                .sip_trunk_username(command.getSip_trunk_username())
                .sip_trunk_password(command.getSip_trunk_password())
                .inbound_agent_id(command.getInbound_agent_id())
                .outbound_agent_id(command.getOutbound_agent_id())
                .build()
        );
        log.info("PhoneCreatedEvent applied for Phone ID: {}", command.getPhone_number());
        return ResponseBody.builder()
                .phone_number(command.getPhone_number())
                .friendly_name(command.getFriendly_name())
                .sip_termination_uri(command.getSip_termination_uri())
                .sip_trunk_username(command.getSip_trunk_username())
                .sip_trunk_password(command.getSip_trunk_password())
                .inbound_agent_id(command.getInbound_agent_id())
                .outbound_agent_id(command.getOutbound_agent_id())
                .build();
    }

    @CommandHandler
    public ResponseBody handle(UpdatePhoneCommand command) throws PhoneException {
        log.info("Handling UpdatePhoneCommand for phone number ID: {}", command.getFriendly_name());

        AggregateLifecycle.apply(PhoneUpdateEvent.builder()
                .phoneNumberId(command.getPhoneNumberId())
                .friendly_name(command.getFriendly_name())
                .sip_termination_uri(command.getSip_termination_uri())
                .sip_trunk_username(command.getSip_trunk_username())
                .sip_trunk_password(command.getSip_trunk_password())
                .inbound_agent_id(command.getInbound_agent_id())
                .outbound_agent_id(command.getOutbound_agent_id())
                .build()
        );
        log.info("PhoneUpdatedEvent applied for Phone ID: {}", command.getFriendly_name());
        return ResponseBody.builder()
                .friendly_name(command.getFriendly_name())
                .sip_termination_uri(command.getSip_termination_uri())
                .sip_trunk_username(command.getSip_trunk_username())
                .sip_trunk_password(command.getSip_trunk_password())
                .inbound_agent_id(command.getInbound_agent_id())
                .outbound_agent_id(command.getOutbound_agent_id())
                .build();
    }

    @CommandHandler
    public void handle(DeletePhoneCommand command){
        AggregateLifecycle.apply(PhoneDeleteEvent.builder()
                .phoneNumberId(command.getPhoneNumberId()).build()
        );
    }



    @EventSourcingHandler
    protected void on(PhoneRegisterEvent event){

        log.info("Event sourcing Phone created event for phone number id: {}", event.getPhoneNumberId());
        this.phoneNumberId = event.getPhoneNumberId();
        this.provider = event.getProvider();
        this.phone_number = event.getPhone_number();
        this.friendly_name = event.getFriendly_name();
        this.sip_termination_uri = event.getSip_termination_uri();
        this.sip_trunk_username = event.getSip_trunk_username();
        this.sip_trunk_password = event.getSip_trunk_password();
        this.inbound_agent_id = event.getInbound_agent_id();
        this.outbound_agent_id = event.getOutbound_agent_id();

        log.info("Event sourcing Phone register aggregate state updated for phone number ID: {}", event.getPhoneNumberId());
    }

    @EventSourcingHandler
    protected void on(PhoneUpdateEvent event){
        log.info("Event sourcing Phone update event for phone number id: {}", event.getFriendly_name());

        this.friendly_name = event.getFriendly_name();
        this.sip_termination_uri = event.getSip_termination_uri();
        this.sip_trunk_username = event.getSip_trunk_username();
        this.sip_trunk_password = event.getSip_trunk_password();
        this.inbound_agent_id = event.getInbound_agent_id();
        this.outbound_agent_id = event.getOutbound_agent_id();

        log.info("Event sourcing Phone register aggregate state updated for phone number ID: {}", event.getFriendly_name());
    }

    @EventSourcingHandler
    protected void on(PhoneDeleteEvent event){
        log.info("Event sourcing Phone delete event for phone number id: {}", event.getPhoneNumberId());
        this.phoneNumberId = event.getPhoneNumberId();
    }



    private void validateCommand(RegisterPhoneCommand command) throws PhoneException {
        if (command.getPhoneNumberId() == null) {
            log.error("Validation failed: phone number is null or empty");
            throw new PhoneException(ErrorResponse.VALIDATION_ERROR, "ID cannot be null or empty");
        }

        if (command.getPhone_number() == null || command.getPhone_number().isBlank()) {
            log.error("Validation failed: Phone is null or empty");
            throw new PhoneException(ErrorResponse.VALIDATION_ERROR, "Phone number cannot be null or empty");
        }
    }

    @ExceptionHandler
    public void handle(CommandExecutionException ex) throws PhoneException {
        log.error("Validation error in aggregate: {}", ex.getMessage());
        throw new PhoneException(ErrorResponse.INTERNAL_ERROR, "Invalid command", ex);
    }
}
