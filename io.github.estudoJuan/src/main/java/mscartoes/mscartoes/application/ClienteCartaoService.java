package mscartoes.mscartoes.application;

import lombok.RequiredArgsConstructor;
import mscartoes.mscartoes.domain.ClienteCartao;
import mscartoes.mscartoes.infra.repository.ClienteCartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

    @Autowired
    private ClienteCartaoRepository repository;

    public List<ClienteCartao> findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
