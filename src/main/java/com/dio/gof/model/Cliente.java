package com.dio.gof.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToOne
    private Endereco endereco;

}
