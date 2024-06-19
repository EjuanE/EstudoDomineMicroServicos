package mscartoes.mscartoes.application;


import lombok.RequiredArgsConstructor;
import mscartoes.mscartoes.Cartao;
import mscartoes.mscartoes.application.DTO.CartaoDTO;
import mscartoes.mscartoes.application.DTO.CartoesClienteDTO;
import mscartoes.mscartoes.domain.ClienteCartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartoesResource {

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private ClienteCartaoService clienteCartaoService;


    @GetMapping
    public String status() {
        return "OK";
    }

    @PostMapping
    public ResponseEntity cadastra(@RequestBody CartaoDTO request) {
        Cartao cartao = request.toModel();
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaMenorIgual(@RequestParam Long renda) {
        List<Cartao> list = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesClienteDTO>> getCartoesByCpf(@RequestParam("cpf") String cpf) {
       List<ClienteCartao> list = clienteCartaoService.findByCpf(cpf);
        List<CartoesClienteDTO> resultList = list.stream()
                .map(CartoesClienteDTO::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}