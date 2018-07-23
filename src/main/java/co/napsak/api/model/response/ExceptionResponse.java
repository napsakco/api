package co.napsak.api.model.response;

import co.napsak.api.model.exception.GlobalException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    private String message;
    private String path;
    private String method;
    private Long time;
}
