package co.napsak.api.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaveEventRequest {

    private String name;
    private String description;
    private Long placeId;
    private Long startDate;
    private Long endDate;
}
