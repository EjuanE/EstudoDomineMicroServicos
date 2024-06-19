package io.github.estudojuan.msavaliadorcredito.domain.model;

import lombok.Data;

@Data
public class Cartao {

    private Long id;
    private String nome;
    private String bandeira;
    private Long limiteBasico;
}
