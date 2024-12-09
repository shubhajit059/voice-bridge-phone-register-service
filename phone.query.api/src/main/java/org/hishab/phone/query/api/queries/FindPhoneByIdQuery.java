package org.hishab.phone.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindPhoneByIdQuery {
    private String phoneNumberId;
}
