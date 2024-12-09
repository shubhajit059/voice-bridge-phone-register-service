package org.hishab.phone.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindAllPhonesQuery {
    private Integer pageSize;
    private String pageToken;
}
