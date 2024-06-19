package io.github.estudoJuan.msclientes.application.representaion;

import io.github.estudoJuan.msclientes.domain.Cliente;
import lombok.Data;

@Data
public class ClienteDTO {

    private String nome;
    private String cpf;
    private Integer idade;

    public Cliente toModel() {
        return new Cliente(this.nome, this.cpf, this.idade);
    }
}
