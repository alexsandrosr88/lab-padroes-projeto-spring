package com.dio.gof.service.impl;

import com.dio.gof.dto.ClientePostDTO;
import com.dio.gof.model.Cliente;
import com.dio.gof.model.Endereco;
import com.dio.gof.repository.ClienteRepository;
import com.dio.gof.repository.EnderecoRepository;
import com.dio.gof.service.ClienteService;
import com.dio.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    // Strategy: Implementar os métodos definidos na interface.
    // Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

    @Override
    public Iterable<Cliente> buscarTodos() {
        // Buscar todos os Clientes.
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        // Buscar Cliente por ID.
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public Cliente inserir(ClientePostDTO clienteDTO) {
        return salvarClienteComCep(clienteDTO);
    }

    @Override
    public void atualizar(Long id, ClientePostDTO clienteDTO) {
        // Buscar Cliente por ID, caso exista:
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            salvarClienteComCep(clienteDTO);
        }
    }

    @Override
    public void deletar(Long id) {
        // Deletar Cliente por ID.
        clienteRepository.deleteById(id);
    }

//    private Cliente salvarClienteComCep(Cliente cliente) {
//        // Verificar se o Endereco do Cliente já existe (pelo CEP).
//        String cep = cliente.getEndereco().getCep();
//        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
//            // Caso não exista, integrar com o ViaCEP e persistir o retorno.
//            Endereco novoEndereco = viaCepService.consultarCep(cep);
//            enderecoRepository.save(novoEndereco);
//            return novoEndereco;
//        });
//        cliente.setEndereco(endereco);
//        // Inserir Cliente, vinculando o Endereco (novo ou existente).
//       return clienteRepository.save(cliente);
//    }

    private Cliente salvarClienteComCep(ClientePostDTO clienteDTO){
        Endereco end = enderecoRepository.findById(clienteDTO.getCep()).orElseGet(()->{
            Endereco endNovo = viaCepService.consultarCep(clienteDTO.getCep());
            endNovo.setNumero(clienteDTO.getNumero());
            endNovo.setComplemento(clienteDTO.getComplemento());
            return enderecoRepository.save(endNovo);
        });
        Cliente novoCliente = new Cliente();
        novoCliente.setNome(clienteDTO.getNome());
        end.setNumero(clienteDTO.getNumero());
        end.setComplemento(clienteDTO.getComplemento());
        novoCliente.setEndereco(end);
        return clienteRepository.save(novoCliente);
    }

}
