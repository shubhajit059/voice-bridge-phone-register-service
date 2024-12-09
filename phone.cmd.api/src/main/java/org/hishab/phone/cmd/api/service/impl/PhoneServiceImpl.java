package org.hishab.phone.cmd.api.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.hishab.phone.cmd.api.commands.DeletePhoneCommand;
import org.hishab.phone.cmd.api.commands.RegisterPhoneCommand;
import org.hishab.phone.cmd.api.commands.UpdatePhoneCommand;
import org.hishab.phone.cmd.api.dto.ErrorResponse;
import org.hishab.phone.cmd.api.dto.PhoneResponse;
import org.hishab.phone.cmd.api.dto.ResponseBody;
import org.hishab.phone.cmd.api.exception.PhoneException;
import org.hishab.phone.cmd.api.service.PhoneService;
import org.hishab.phone.core.model.PhoneRequest;
import org.hishab.phone.core.model.UpdatePhoneRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class PhoneServiceImpl implements PhoneService {

    private final CommandGateway commandGateway;

    public PhoneServiceImpl(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<PhoneResponse> registerPhone(PhoneRequest phoneRequest) throws PhoneException {
        String phone_number_id = UUID.randomUUID().toString();

        return commandGateway.send(RegisterPhoneCommand.builder()
                .phoneNumberId(phone_number_id)
                .phone_number(phoneRequest.getPhone_number())
                .provider(phoneRequest.getProvider())
                .friendly_name(phoneRequest.getFriendly_name())
                .inbound_agent_id(phoneRequest.getInbound_agent_id())
                .outbound_agent_id(phoneRequest.getOutbound_agent_id())
                .sip_termination_uri(phoneRequest.getSip_termination_uri())
                .sip_trunk_username(phoneRequest.getSip_trunk_username())
                .sip_trunk_password(phoneRequest.getSip_trunk_password())
                .build())
                .thenApply(result -> {
                    ResponseBody response = (ResponseBody) result;
                    return new PhoneResponse(200, "Phone Created Successfully", ResponseBody
                            .builder()
                            .phone_number(response.getPhone_number())
                            .friendly_name(response.getFriendly_name())
                            .sip_termination_uri(response.getSip_termination_uri())
                            .sip_trunk_username(response.getSip_trunk_username())
                            .sip_trunk_password(response.getSip_trunk_password())
                            .inbound_agent_id(response.getInbound_agent_id())
                            .outbound_agent_id(response.getOutbound_agent_id())
                            .build());
                }).exceptionally(ex -> {
                    log.error("Failed to created phone number. phone number {}, reason is {}", phoneRequest.getPhone_number(), ex.getMessage());
                    try {
                        throw new PhoneException(ErrorResponse.INTERNAL_ERROR, "Failed to created phone", ex);
                    } catch (PhoneException e) {
                        throw new CommandExecutionException("Failed to created phone", ex);
                    }
                });
    }

    @Override
    public CompletableFuture<PhoneResponse> updatePhone(String phone_number_id, UpdatePhoneRequest updatePhoneRequest) throws PhoneException {
        return commandGateway.send(UpdatePhoneCommand
                        .builder()
                        .phoneNumberId(phone_number_id)
                        .friendly_name(updatePhoneRequest.getFriendly_name())
                        .inbound_agent_id(updatePhoneRequest.getInbound_agent_id())
                        .outbound_agent_id(updatePhoneRequest.getOutbound_agent_id())
                        .sip_termination_uri(updatePhoneRequest.getSip_termination_uri())
                        .sip_trunk_username(updatePhoneRequest.getSip_trunk_username())
                        .sip_trunk_password(updatePhoneRequest.getSip_trunk_password())
                        .build())
                .thenApply(result -> {
                    ResponseBody response = (ResponseBody) result;
                    return new PhoneResponse(200, "Phone Created Successfully", ResponseBody
                            .builder()
                            .phone_number(response.getPhone_number())
                            .friendly_name(response.getFriendly_name())
                            .sip_termination_uri(response.getSip_termination_uri())
                            .sip_trunk_username(response.getSip_trunk_username())
                            .sip_trunk_password(response.getSip_trunk_password())
                            .inbound_agent_id(response.getInbound_agent_id())
                            .outbound_agent_id(response.getOutbound_agent_id())
                            .build());
                }).exceptionally(ex -> {
                    log.error("Failed to created phone number. phone number {}, reason is {}", updatePhoneRequest.getFriendly_name(), ex.getMessage());
                    try {
                        throw new PhoneException(ErrorResponse.INTERNAL_ERROR, "Failed to created phone", ex);
                    } catch (PhoneException e) {
                        throw new CommandExecutionException("Failed to created phone", ex);
                    }
                });
    }

    @Override
    public CompletableFuture<Void> deletePhone(String phone_number_id) throws PhoneException {
        return commandGateway.send(new DeletePhoneCommand(phone_number_id));
    }


}
