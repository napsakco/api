package co.napsak.api.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class SavePlaceRequest {

    private String name;
    private String address;
    private BigDecimal lat;
    private BigDecimal lng;
}
