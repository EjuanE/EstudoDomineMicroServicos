package io.github.estudojuan.msavaliadorcredito.domain.model;

import lombok.Data;

@Data
public class CartaoCliente {

    private String nome;
    private String bandeira;
    private Long limiteDisponivel;
}
