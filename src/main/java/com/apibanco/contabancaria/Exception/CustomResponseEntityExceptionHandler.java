package com.apibanco.contabancaria.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ContaNotFoundException.class)
    public ResponseEntity<Object> handleContaNotFoundException(ContaNotFoundException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(TransacaoInvalidaException.class)
    public ResponseEntity<Object> handleTransacaoInvalidaException(TransacaoInvalidaException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    static class CustomErrorResponse {
        private final HttpStatus status;
        private final String message;

        public CustomErrorResponse(HttpStatus status, String message) {
            this.status = status;
            this.message = message;
        }

        public HttpStatus getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }
}
