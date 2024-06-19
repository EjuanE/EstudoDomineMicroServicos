package io.github.estudojuan.msavaliadorcredito.application.exception;

import lombok.Getter;

public class ErroComunicacaoMicroservicesException extends Exception {

    @Getter
    private Integer status;

    public ErroComunicacaoMicroservicesException(String message) {
        super(message);
        this.status = status;

    }
}
