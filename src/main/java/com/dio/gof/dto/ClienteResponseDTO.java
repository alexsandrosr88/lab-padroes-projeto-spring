package com.dio.gof.dto;

import com.dio.gof.model.Cliente;
import com.dio.gof.model.Endereco;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
public class ClienteResponseDTO {

    private Long id;
    private String nome;
    private Endereco endereco;


    public ClienteResponseDTO(Cliente cliente) {
        id = cliente.getId();
        nome = cliente.getNome();
        endereco = cliente.getEndereco();
    }
}
