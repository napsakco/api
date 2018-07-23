package co.napsak.api.model.exception;

import org.springframework.http.HttpStatus;

public class GlobalException extends RuntimeException {

    private HttpStatus status;
    private String messageCode;

    public GlobalException(String messageCode) {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.messageCode = messageCode;
    }

    public GlobalException(HttpStatus status, String messageCode) {
        this.status = status;
        this.messageCode = messageCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessageCode() {
        return messageCode;
    }
}
