package org.hishab.phone.query.api.controller;

import org.hishab.phone.query.api.entity.PhoneEntity;
import org.hishab.phone.core.common.QueryNames;
import org.hishab.phone.query.api.response.PhoneResponse;
import org.hishab.phone.query.api.response.PhonesResponse;
import org.hishab.phone.query.api.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/v1")
public class PhoneQueryController {

    private final PhoneService phoneService;

    @Autowired
    public PhoneQueryController(PhoneService phoneService){
        this.phoneService = phoneService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/phone-numbers")
    public CompletableFuture<PhonesResponse> findAll(@RequestParam(required = false, defaultValue = "20") Integer page_size,
                                                     @RequestParam(required = false) String page_token){
        return phoneService.findAllPhone(page_size, page_token);

    }

    @CrossOrigin(origins = "*")
    @GetMapping("/phone-numbers/{phone_number_id}")
    public CompletableFuture<ResponseEntity<PhoneResponse>> findById(@PathVariable("phone_number_id") String phoneNumberId) {
        return phoneService.findPhoneById(phoneNumberId)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.status(404).body(null));
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/phone-numbers/internal/ai_agent_info")
    public CompletableFuture<ResponseEntity<List<Object>>> getAgentInfo(
            @RequestParam("phone_number_e164_str") String phone_number) {
        return phoneService.findAgentInfoByPhoneNumber(phone_number)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.status(404).body(null));
    }
}
