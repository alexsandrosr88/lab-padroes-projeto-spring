package com.dio.gof.controller;

import com.dio.gof.dto.ClientePostDTO;
import com.dio.gof.model.Cliente;
import com.dio.gof.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {

    @Autowired
    private ClienteService service;

    @GetMapping
    Iterable<Cliente> buscaTodos(){
        return service.buscarTodos();
    }

    @GetMapping("{id}")
    Cliente buscaPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Cliente inserir(@RequestBody ClientePostDTO clienteDTO){
        return service.salvarCliente(clienteDTO);
    }

    @PutMapping("{id}")
    void atualizar(Long id, @RequestBody ClientePostDTO clienteDTO){
        service.atualizar(id,clienteDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    void deletarCliente(Long id){
        service.deletar(id);
    }
}
