package co.napsak.api.model.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends GlobalException {

    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    public NotFoundException(String messageCode) {
        super(STATUS, messageCode);
    }
}
