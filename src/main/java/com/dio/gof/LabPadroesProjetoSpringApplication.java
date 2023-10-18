package com.dio.gof;

import com.dio.gof.model.Endereco;
import com.dio.gof.service.ViaCepService;
import com.dio.gof.service.impl.ClienteServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class LabPadroesProjetoSpringApplication{

	@Autowired
	ViaCepService viaCepService;

	@Autowired
	private static final Logger log = LoggerFactory.getLogger(LabPadroesProjetoSpringApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LabPadroesProjetoSpringApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ClienteServiceImpl clienteService){
		return (arg) ->{
//			log.info("\n\nTeste do  viaCep");
//			Endereco end = viaCepService.consultarCep("05547025");
//			System.out.println(end.getCep());
		};
	}
}