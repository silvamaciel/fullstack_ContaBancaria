package com.apibanco.contabancaria.Exception;

public class TransacaoInvalidaException extends RuntimeException {
    public TransacaoInvalidaException(String message) {
        super(message);
    }
}
