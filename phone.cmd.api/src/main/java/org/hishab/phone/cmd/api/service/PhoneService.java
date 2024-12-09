package org.hishab.phone.cmd.api.service;

import org.hishab.phone.cmd.api.dto.PhoneResponse;
import org.hishab.phone.cmd.api.exception.PhoneException;
import org.hishab.phone.core.model.PhoneRequest;
import org.hishab.phone.core.model.UpdatePhoneRequest;

import java.util.concurrent.CompletableFuture;


public interface PhoneService {
    CompletableFuture<PhoneResponse> registerPhone(PhoneRequest phoneRequest) throws PhoneException;
    CompletableFuture<PhoneResponse> updatePhone(String phone_number_id, UpdatePhoneRequest updatePhoneRequest) throws PhoneException;
    CompletableFuture<Void> deletePhone(String phone_number_id) throws PhoneException;
}
