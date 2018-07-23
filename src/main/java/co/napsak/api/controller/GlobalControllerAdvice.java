package co.napsak.api.controller;

import co.napsak.api.util.MessageUtil;
import co.napsak.api.model.exception.GlobalException;
import co.napsak.api.model.exception.NotFoundException;
import co.napsak.api.model.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

    private final MessageUtil messageUtil;

    public GlobalControllerAdvice(MessageUtil messageUtil) {
        this.messageUtil = messageUtil;
    }

    @ExceptionHandler(Throwable.class)
    public Mono<ResponseEntity<ExceptionResponse>> handleThrowable(Throwable exception) {
        log.error("Unhandled exception", exception);
        GlobalException globalException = new GlobalException("unknownException");
        return Mono.just(constructExceptionResponse(globalException));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ExceptionResponse>> handleException(Exception exception) {
        log.error("Unhandled exception", exception);
        GlobalException globalException = new GlobalException("unknownException");
        return Mono.just(constructExceptionResponse(globalException));
    }

    @ExceptionHandler(RuntimeException.class)
    public Mono<ResponseEntity<ExceptionResponse>> handleRuntimeException(RuntimeException exception) {
        log.error("Unhandled exception", exception);
        GlobalException globalException = new GlobalException("unknownException");
        return Mono.just(constructExceptionResponse(globalException));
    }

    @ExceptionHandler(GlobalException.class)
    public Mono<ResponseEntity<ExceptionResponse>> handleGlobalException(GlobalException exception) {
        log.error("Global exception", exception);
        return Mono.just(constructExceptionResponse(exception));
    }

    @ExceptionHandler(NotFoundException.class)
    public Mono<ResponseEntity<ExceptionResponse>> handleNotFoundException(NotFoundException exception) {
        log.error("Not found exception", exception);
        return Mono.just(constructExceptionResponse(exception));
    }

    private ResponseEntity<ExceptionResponse> constructExceptionResponse(GlobalException globalException) {
        ExceptionResponse response = ExceptionResponse.builder()
                .message(messageUtil.getMessage(globalException.getMessageCode()))
                .time(System.currentTimeMillis())
                .build();

        return ResponseEntity
                .status(globalException.getStatus())
                .body(response);
    }
}
