package org.hishab.phone.query.api.service.impl;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.hishab.phone.query.api.queries.FindAgentListbyPhoneNum;
import org.hishab.phone.query.api.queries.FindAllPhonesQuery;
import org.hishab.phone.query.api.queries.FindPhoneByIdQuery;
import org.hishab.phone.query.api.response.PhoneResponse;
import org.hishab.phone.query.api.response.PhonesResponse;
import org.hishab.phone.query.api.service.PhoneService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PhoneServiceImpl implements PhoneService {

    private final QueryGateway queryGateway;


    public PhoneServiceImpl(QueryGateway queryGateway){
        this.queryGateway = queryGateway;
    }


    @Override
    public CompletableFuture<PhonesResponse> findAllPhone(Integer page_size, String page_token) {
        return queryGateway.query(
                new FindAllPhonesQuery(page_size, page_token),
                ResponseTypes.instanceOf(PhonesResponse.class)
        );
    }

    @Override
    public CompletableFuture<PhoneResponse> findPhoneById(String phone_number_id) {
        return queryGateway.query(
                new FindPhoneByIdQuery(phone_number_id),
                ResponseTypes.instanceOf(PhoneResponse.class)
        );
    }

    @Override
    public CompletableFuture<List<Object>> findAgentInfoByPhoneNumber(String phone_number) {
        return queryGateway.query(
                new FindAgentListbyPhoneNum(phone_number),
                ResponseTypes.multipleInstancesOf(Object.class)
        );
    }
}
