package org.hishab.phone.query.api.handlers.event;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.MetaData;
import org.hishab.phone.core.enums.PhoneStatus;
import org.hishab.phone.core.events.PhoneDeleteEvent;
import org.hishab.phone.core.events.PhoneRegisterEvent;
import org.hishab.phone.core.events.PhoneUpdateEvent;
import org.hishab.phone.query.api.entity.PhoneEntity;
import org.hishab.phone.query.api.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Slf4j
@Service
public class PhoneEventHandler {

    private final PhoneRepository phoneRepository;

    @Autowired
    public PhoneEventHandler(PhoneRepository phoneRepository){
        this.phoneRepository = phoneRepository;
    }

    @EventHandler
    public void on(PhoneRegisterEvent event, MetaData metaData){
        String traceId = (String) metaData.getOrDefault("traceId", "No Trace ID");
        System.out.println("Trace ID in query module: " + traceId);
        log.info("Received create PhoneRegisterEvent with ID: {}", event.getPhoneNumberId());
        phoneRepository.save(
                PhoneEntity.builder()
                        .phoneNumberId(event.getPhoneNumberId())
                        .provider(event.getProvider())
                        .phone_number(event.getPhone_number())
                        .friendly_name(event.getFriendly_name())
                        .sip_termination_uri(event.getSip_termination_uri())
                        .sip_trunk_username(event.getSip_trunk_username())
                        .sip_trunk_password(event.getSip_trunk_password())
                        .inbound_agent_id(event.getInbound_agent_id())
                        .outbound_agent_id(event.getOutbound_agent_id())
                        .createdAt(Instant.now())
                        .modifiedAt(Instant.now())
                        .phoneStatus(PhoneStatus.ACTIVE)
                        .build()

        );
    }

    @EventHandler
    public void on(PhoneUpdateEvent event){
        log.info("Received update PhoneRegisterEvent with ID: {}", event.getFriendly_name());
        Optional<PhoneEntity> optionalPhoneEntity = phoneRepository.findById(event.getPhoneNumberId());
        if(optionalPhoneEntity.isPresent()){
            phoneRepository.save(PhoneEntity.builder()
                    .friendly_name(event.getFriendly_name())
                    .inbound_agent_id(event.getInbound_agent_id())
                    .outbound_agent_id(event.getOutbound_agent_id())
                    .sip_termination_uri(event.getSip_termination_uri())
                    .sip_trunk_username(event.getSip_trunk_username())
                    .sip_trunk_password(event.getSip_trunk_password())
                    .modifiedAt(Instant.now())
                    .build()
            );
        }
    }

    @EventHandler
    public void on(PhoneDeleteEvent event){
        log.info("Received delete PhoneRegisterEvent with ID: {}", event.getPhoneNumberId());
        boolean isExist = phoneRepository.existsById(event.getPhoneNumberId());
        if(!isExist){
            throw new RuntimeException("Phone number not found for ID: " + event.getPhoneNumberId());
        }
        phoneRepository.deleteById(event.getPhoneNumberId());

    }

}
