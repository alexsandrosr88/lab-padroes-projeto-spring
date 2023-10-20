package com.dio.gof.service;

import com.dio.gof.dto.ClienteRequestDTO;
import com.dio.gof.dto.ClienteResponseDTO;

import java.util.List;

public interface ClienteService {
    List<ClienteResponseDTO> buscarTodos();
    ClienteResponseDTO buscarPorId(Long id);
    ClienteResponseDTO salvarClienteComEndereco(ClienteRequestDTO clienteRequestDTO);
    ClienteResponseDTO atualizarClienteComEndereco(Long id, ClienteRequestDTO ClienteRequestDTO);
    void deletarCliente(Long id);

}
