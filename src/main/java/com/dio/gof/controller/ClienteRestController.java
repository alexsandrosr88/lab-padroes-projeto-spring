package com.dio.gof.controller;

import com.dio.gof.dto.ClienteRequestDTO;
import com.dio.gof.dto.ClienteResponseDTO;
import com.dio.gof.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {

    @Autowired
    private ClienteService service;

    @GetMapping
    List<ClienteResponseDTO> buscaTodos(){
        return service.buscarTodos();
    }

    @GetMapping("{id}")
    ClienteResponseDTO buscaPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    ClienteResponseDTO salvar(@RequestBody ClienteRequestDTO clienteRequestDTO){
        return service.salvarClienteComEndereco(clienteRequestDTO);
    }

    @PutMapping("{id}")
    ClienteResponseDTO atualizar(Long id, @RequestBody ClienteRequestDTO clienteRequestDTO){
        return service.atualizarClienteComEndereco(id, clienteRequestDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    void deletarCliente(Long id){
        service.deletarCliente(id);
    }
}
