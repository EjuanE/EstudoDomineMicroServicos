package io.github.estudojuan.msavaliadorcredito.domain.model;

import lombok.Data;

@Data
public class CartaoAprovado {

    private String cartao;
    private String bandeira;
    private Long limiteAprovado;
}
