package mscartoes.mscartoes.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mscartoes.mscartoes.domain.ClienteCartao;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CartoesClienteDTO {
    private String nome;
    private String bandeira;
    private Long limiteDisponivel;

    public CartoesClienteDTO(String nome, String string, Long limite) {
        this.nome = nome;
        this.bandeira = string;
        this.limiteDisponivel = limite;
    }


    public static CartoesClienteDTO fromModel(ClienteCartao clienteCartao) {
        return new CartoesClienteDTO(
                clienteCartao.getCartao().getNome(),
                clienteCartao.getCartao().getBandeira().toString(),
                clienteCartao.getLimite()
        );
    }
}
