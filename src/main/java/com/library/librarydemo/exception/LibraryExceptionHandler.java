package com.library.librarydemo.exception;

import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@ControllerAdvice
@Slf4j
public class LibraryExceptionHandler implements ErrorWebExceptionHandler {

    @ExceptionHandler(LibraryDemoException.class)
    public final ResponseEntity<Void> handlePartnerAgentExceptions(LibraryDemoException exception) {
        log.error("Handling LibraryDemoException: ", exception);
        return new ResponseEntity<>(exception.getHttpStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Void> handleConstraintViolationException(ConstraintViolationException exception) {
        log.error("Handling ConstraintViolationException: ", exception);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        log.error("Error occurred:", ex);
        ServerHttpResponse response = exchange.getResponse();
        return response.writeWith(Mono.fromSupplier(() -> response.bufferFactory().wrap(new byte[0])));
    }
}
