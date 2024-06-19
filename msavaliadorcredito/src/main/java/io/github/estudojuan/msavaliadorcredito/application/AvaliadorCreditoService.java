package io.github.estudojuan.msavaliadorcredito.application;

import feign.FeignException;
import io.github.estudojuan.msavaliadorcredito.application.exception.DadosClienteNotFoundException;
import io.github.estudojuan.msavaliadorcredito.application.exception.ErroComunicacaoMicroservicesException;
import io.github.estudojuan.msavaliadorcredito.domain.model.*;
import io.github.estudojuan.msavaliadorcredito.infra.clients.CartoesResourceClient;
import io.github.estudojuan.msavaliadorcredito.infra.clients.ClienteResourceClient;
import io.github.estudojuan.msavaliadorcredito.infra.mqueue.SolicitacaoEmissaoCartaoPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient resourceClient;

    private final CartoesResourceClient cartoesResourceClient;

    private final SolicitacaoEmissaoCartaoPublisher solicitarEmissaoCartaoPublisher;

    public SituacaoCliente obterSituacaoCliente(String cpf)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = resourceClient.dadosCliente(cpf);

            ResponseEntity<List<CartaoCliente>> cartoesResponse = cartoesResourceClient.getCartoesByCpf(cpf);

            return SituacaoCliente.builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(cartoesResponse.getBody())
                    .build();
        }catch (FeignException.FeignClientException e){
            int status = e.status();
            if(status == HttpStatus.NOT_FOUND.value()){
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage());
        }

    }
    public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException { {

        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = resourceClient.dadosCliente(cpf);
            DadosCliente dadosCliente = dadosClienteResponse.getBody();
            ResponseEntity<List<Cartao>> cartoesResponse = cartoesResourceClient.getCartoesRendaMenorIgual(renda);
            List<Cartao> cartoes = cartoesResponse.getBody();


           var  ListaCartoesAprovados = cartoes.stream().map(cartao -> {


                       Long limiteBasico = cartao.getLimiteBasico();
                       Long idade = dadosCliente.getIdade();

                       Long fator = idade / 10;
                       Long limiteAprovado = fator * limiteBasico;


                        CartaoAprovado cartaoAprovado = new CartaoAprovado();
                        cartaoAprovado.setCartao(cartao.getNome());
                        cartaoAprovado.setBandeira(cartao.getBandeira());
                        cartaoAprovado.setLimiteAprovado(limiteAprovado);

                        return cartaoAprovado;
                    }).collect(Collectors.toList());

           return  new RetornoAvaliacaoCliente(ListaCartoesAprovados);

        }catch (FeignException.FeignClientException e){
            int status = e.status();
            if(status == HttpStatus.NOT_FOUND.value()){
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage());
        }
    }




    }
    public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados) throws ErroComunicacaoMicroservicesException {
        try{
            solicitarEmissaoCartaoPublisher.solicitarCartao(dados);
            var protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartao(protocolo);
        }catch (Exception e){
            throw new ErroComunicacaoMicroservicesException(e.getMessage());
            }


    }


}
