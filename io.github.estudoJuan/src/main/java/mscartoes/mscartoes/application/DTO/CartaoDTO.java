package mscartoes.mscartoes.application.DTO;

import lombok.Data;
import mscartoes.mscartoes.BandeiraCartao;
import mscartoes.mscartoes.Cartao;

import java.math.BigDecimal;

@Data
public class CartaoDTO {

    private String nome;
    private Long renda;
    private Long limiteBasico;
    private BandeiraCartao bandeira;

    public Cartao toModel(String nome, Long renda, Long limiteBasico, BandeiraCartao bandeira) {
        return new Cartao(nome, renda, limiteBasico, bandeira);
    }


    public Cartao toModel() {
        return new Cartao(this.nome, this.renda, this.limiteBasico, this.bandeira);
    }
}
