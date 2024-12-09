package org.hishab.phone.cmd.api.controller;

import org.hishab.phone.cmd.api.dto.PhoneResponse;
import org.hishab.phone.cmd.api.exception.PhoneException;
import org.hishab.phone.cmd.api.service.PhoneService;
import org.hishab.phone.core.model.PhoneRequest;
import org.hishab.phone.core.model.UpdatePhoneRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/v1")
public class PhoneController {

    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService){
        this.phoneService = phoneService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/phone-numbers",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CompletableFuture<PhoneResponse> registerPhone(@RequestBody PhoneRequest phoneRequest) throws PhoneException {
        return phoneService.registerPhone(phoneRequest);
    }


    @CrossOrigin(origins = "*")
    @PatchMapping(value = "/phone-numbers/{phone_number_id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture<PhoneResponse> updatePhone(@PathVariable("phone_number_id") String phone_number_id, @RequestBody UpdatePhoneRequest updatePhoneRequest) throws PhoneException {
        return phoneService.updatePhone(phone_number_id, updatePhoneRequest);
    }

    @DeleteMapping("/phone-numbers/{phone_number_id}")
    public CompletableFuture<Void> deleteById(@PathVariable("phone_number_id") String phoneNumberId) throws PhoneException {
        return phoneService.deletePhone(phoneNumberId);
    }

}
