package co.napsak.api.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateEventRequest extends SaveEventRequest {

    private Long id;
}
