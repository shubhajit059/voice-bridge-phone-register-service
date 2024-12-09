package org.hishab.phone.query.api.handlers.queries;

import org.axonframework.queryhandling.QueryHandler;
import org.hishab.phone.query.api.entity.PhoneEntity;
import org.hishab.phone.query.api.queries.FindAgentListbyPhoneNum;
import org.hishab.phone.query.api.queries.FindAllPhonesQuery;
import org.hishab.phone.query.api.queries.FindPhoneByIdQuery;
import org.hishab.phone.query.api.repository.PhoneRepository;
import org.hishab.phone.query.api.response.PhoneResponse;
import org.hishab.phone.query.api.response.PhonesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class PhoneQueriesHandler {

    @Value("${external_ai_agent_info_api}")
    private String agentExternalApiUrl;

    private final PhoneRepository phoneRepository;
    private final WebClient webClient;

    @Autowired
    public PhoneQueriesHandler(PhoneRepository phoneRepository, WebClient webClient){
        this.phoneRepository = phoneRepository;
        this.webClient = webClient;
    }

    @QueryHandler
    public PhonesResponse handle(FindAllPhonesQuery query) {
        int pageSize = query.getPageSize() != null ? query.getPageSize() : 20; // Default page size
        String pageToken = query.getPageToken();

        List<PhoneEntity> allPhones;

        if (pageToken == null || pageToken.isEmpty()) {
            // Fetch first page
            Pageable pageable = PageRequest.of(0, pageSize, Sort.by("createdAt").ascending());
            allPhones = phoneRepository.findAll(pageable).getContent();
        } else {
            // Fetch subsequent pages based on cursor
            PhoneEntity lastPhone = phoneRepository.findById(pageToken).orElse(null);

            if (lastPhone != null) {
                Instant createdAtCursor = lastPhone.getCreatedAt();

                Pageable pageable = PageRequest.of(0, pageSize, Sort.by("createdAt").ascending());
                allPhones = phoneRepository
                        .findByCreatedAtGreaterThan(createdAtCursor, pageable)
                        .getContent();
            } else {
                allPhones = List.of(); // Return empty if token is invalid
            }
        }

        // Determine the next page token
        String nextPageToken = allPhones.size() == pageSize
                ? allPhones.get(allPhones.size() - 1).getPhoneNumberId()
                : null;

        return new PhonesResponse(allPhones, nextPageToken);
    }


    @QueryHandler
    public PhoneResponse handle(FindPhoneByIdQuery query) {
        Optional<PhoneEntity> phoneEntityOptional = phoneRepository.findById(query.getPhoneNumberId());

        if (phoneEntityOptional.isEmpty()) {
            throw new RuntimeException("Phone number not found for ID: " + query.getPhoneNumberId());
        }

        PhoneEntity phoneEntity = phoneEntityOptional.get();

        return new PhoneResponse(
                phoneEntity.getPhoneNumberId(),
                phoneEntity.getPhone_number(),
                phoneEntity.getFriendly_name(),
                phoneEntity.getSip_termination_uri(),
                phoneEntity.getSip_trunk_username(),
                phoneEntity.getSip_trunk_password(),
                phoneEntity.getInbound_agent_id(),
                phoneEntity.getOutbound_agent_id()
        );
    }

    @QueryHandler
    public List<Object> handle(FindAgentListbyPhoneNum query){
        List<PhoneEntity> phoneEntities = phoneRepository.findByPhone_number(query.getPhone_number());

        if (phoneEntities.isEmpty()) {
            throw new RuntimeException("No agent information found for phone number: " + query.getPhone_number());
        }
        Set<String> agentIds = phoneEntities.stream()
                .flatMap(phone -> Arrays.stream(new String[]{phone.getInbound_agent_id(), phone.getOutbound_agent_id()}))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        String externalApiUrl = agentExternalApiUrl;

        String agentIdsParam = agentIds.stream()
                .map(id -> "agent_ids=" + id)
                .collect(Collectors.joining("&"));

        String apiUrl = externalApiUrl + "?" + agentIdsParam+ "&page_size=20";

        Map<String, Object> response = webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(Map.class) // Reactive response handling
                .block(); // Block for simplicity in query handler context

        if (response == null || !response.containsKey("data")) {
            throw new RuntimeException("Failed to fetch agent information from external API");
        }

        // Extract and return the "data" field
        return (List<Object>) response.get("data");
    }

}
