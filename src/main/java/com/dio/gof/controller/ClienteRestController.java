package com.dio.gof.controller;

import com.dio.gof.model.Cliente;
import com.dio.gof.service.ClienteService;
import com.dio.gof.service.impl.ClienteServiceImpl;
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
        return service.buscaPorId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Cliente inserir(@RequestBody Cliente cliente){
        return service.inserir(cliente);
    }

    @PutMapping
    Cliente atualizar(@PathVariable Long id, @RequestBody Cliente cliente){
        return service.atualizar(id,cliente);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    void deletarCliente(Long id){
        service.deletar(id);
    }

}
