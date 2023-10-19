package com.dio.gof.service;

import com.dio.gof.dto.ClienteDTO;
import com.dio.gof.model.Cliente;

public interface ClienteService {
    Iterable<Cliente> buscarTodos();
    Cliente buscarPorId(Long id);
    Cliente salvarCliente (ClienteDTO clienteDTO);
    Cliente atualizarCliente (Long id, ClienteDTO ClienteDTO);
    void deletar(Long id);

}
