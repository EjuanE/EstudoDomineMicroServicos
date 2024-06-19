package io.github.estudojuan.msavaliadorcredito.application.exception;

public class DadosClienteNotFoundException extends Exception{

    public DadosClienteNotFoundException() {
        super("Dados do cliente não encontrados");
    }
}
