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
    public Cliente salvarCliente(ClientePostDTO clienteDTO) {
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

    private Cliente salvarClienteComCep(ClientePostDTO clienteDTO) {
        Endereco end = existsEndereco(clienteDTO).orElseGet(() -> salvarEndereco(clienteDTO));
        return clienteRepository.save(new Cliente(clienteDTO.getNome(), end));
    }

    private Optional<Endereco> existsEndereco(ClientePostDTO clientePostDTO) {
        validaCEP(clientePostDTO);
        return Optional.ofNullable(enderecoRepository.existsEndereco(clientePostDTO.getCep(),
                clientePostDTO.getNumero(),
                clientePostDTO.getComplemento()));
    }

    private Endereco salvarEndereco(ClientePostDTO clientePostDTO) {
        Endereco endEntity = new Endereco(viaCepService.consultarCep(clientePostDTO.getCep()));
        endEntity.setNumero(clientePostDTO.getNumero());
        endEntity.setComplemento(clientePostDTO.getComplemento());

        return enderecoRepository.save(endEntity);
    }

    private void validaCEP(ClientePostDTO clientePostDTO){
        if(!clientePostDTO.getCep().substring(5,6).equalsIgnoreCase("-")){
            String cep = clientePostDTO.getCep();
            clientePostDTO.setCep(cep.substring(0,5).concat("-").concat(cep.substring(5,8)));
        }
    }
}
