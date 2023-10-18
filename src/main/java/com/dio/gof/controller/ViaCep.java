package com.dio.gof.controller;

import com.dio.gof.model.Endereco;
import com.dio.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cep")
class ViaCep {

    @Autowired
    private ViaCepService service;

    @GetMapping("{cep}")
    public Endereco pesquisarCep(@PathVariable String cep){
        return service.consultarCep(cep);
    }
}
