package com.dio.gof.service;

import com.dio.gof.model.Cliente;

public interface ClienteService {
    Iterable<Cliente> buscarTodos();
    Cliente buscaPorId(Long id);
    Cliente inserir (Cliente cliente);
    Cliente atualizar (Long id, Cliente cliente);
    void deletar(Long id);

}
