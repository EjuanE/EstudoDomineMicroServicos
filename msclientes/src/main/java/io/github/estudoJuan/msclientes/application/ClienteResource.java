package io.github.estudoJuan.msclientes.application;

//Mesma coisa que ClienteController

import io.github.estudoJuan.msclientes.application.representaion.ClienteDTO;
import io.github.estudoJuan.msclientes.domain.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    private static final Logger log = LoggerFactory.getLogger(ClienteResource.class);
    private final ClienteService service;

    public ClienteResource(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public String status() {
        log.info("Chamando o método status");
        return "OK";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteDTO dto) {
      Cliente cliente =  dto.toModel();
      service.save(cliente);

      URI headerLocation;
        headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf()).toUri();
        return ResponseEntity.created(headerLocation).build();

    }

    @GetMapping(params = "cpf")
    public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf) {
       var cliente = service.getByCpf(cpf);

       if (cliente.isEmpty()){
           return ResponseEntity.notFound().build();
       }
        return ResponseEntity.ok(cliente);
    }
}
