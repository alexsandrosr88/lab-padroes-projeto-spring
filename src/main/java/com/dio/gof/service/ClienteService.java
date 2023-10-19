package com.dio.gof.service;

import com.dio.gof.dto.ClientePostDTO;
import com.dio.gof.model.Cliente;

public interface ClienteService {
    Iterable<Cliente> buscarTodos();
    Cliente buscarPorId(Long id);
    Cliente salvarCliente (ClientePostDTO clienteDTO);
    void atualizar (Long id, ClientePostDTO ClienteDTO);
    void deletar(Long id);

}
