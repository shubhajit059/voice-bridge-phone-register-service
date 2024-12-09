package org.hishab.phone.query.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hishab.phone.query.api.entity.PhoneEntity;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhonesResponse {

    @JsonProperty("phone_numbers")
    private List<PhoneEntity> phoneNumbers;

    @JsonProperty("next_page_token")
    private String nextPageToken;
}
