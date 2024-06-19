package mscartoes.mscartoes.application;


import lombok.RequiredArgsConstructor;
import mscartoes.mscartoes.Cartao;
import mscartoes.mscartoes.infra.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoService {

    @Autowired
    private  CartaoRepository cartaoRepository;




    @Transactional
    public void save(Cartao cartao) {
        cartaoRepository.save(cartao);
    }

    public List<Cartao> getCartoesRendaMenorIgual(Long renda) {
        return cartaoRepository.findByRendaLessThanEqual(renda);
    }
}
