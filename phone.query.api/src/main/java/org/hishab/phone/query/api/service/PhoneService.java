package org.hishab.phone.query.api.service;

import org.hishab.phone.query.api.response.PhoneResponse;
import org.hishab.phone.query.api.response.PhonesResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PhoneService {
    CompletableFuture<PhonesResponse> findAllPhone(Integer page_size, String page_token);
    CompletableFuture<PhoneResponse> findPhoneById(String phone_number_id);
    CompletableFuture<List<Object>> findAgentInfoByPhoneNumber(String phone_number);
}
