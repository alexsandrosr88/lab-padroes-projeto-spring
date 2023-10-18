package com.dio.gof.service.impl;

import com.dio.gof.model.Cliente;
import com.dio.gof.repository.ClienteRepository;
import com.dio.gof.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return null;
    }

    @Override
    public Cliente buscaPorId(Long id) {
        return null;
    }

    @Override
    public Cliente inserir(Cliente cliente) {
        return null;
    }

    @Override
    public Cliente atualizar(Long id, Cliente cliente) {
    return null;
    }

    @Override
    public void deletar(Long id) {

    }
}
