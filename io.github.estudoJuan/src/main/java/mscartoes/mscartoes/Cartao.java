package mscartoes.mscartoes;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(javax.persistence.EnumType.STRING)
    private BandeiraCartao bandeira;
    private Long renda;
    private Long limiteBasico;

    public Cartao(String nome, Long renda, Long limiteBasico, BandeiraCartao bandeira) {
        this.nome = nome;
        this.renda = renda;
        this.limiteBasico = limiteBasico;
        this.bandeira = bandeira;
    }

}
