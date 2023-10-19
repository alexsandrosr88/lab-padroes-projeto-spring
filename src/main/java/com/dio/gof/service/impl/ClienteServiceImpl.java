package com.dio.gof.service.impl;

import com.dio.gof.dto.ClienteDTO;
import com.dio.gof.model.Cliente;
import com.dio.gof.model.Endereco;
import com.dio.gof.repository.ClienteRepository;
import com.dio.gof.repository.EnderecoRepository;
import com.dio.gof.service.ClienteService;
import com.dio.gof.service.Exception.CampoObrigatorioException;
import com.dio.gof.service.Exception.RecursoNaoEncontrado;
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
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        validaClienteID(id);
        return clienteRepository.findById(id).get();
    }

    @Override
    public Cliente salvarCliente(ClienteDTO clienteDTO) {
        return salvarClienteComCep(clienteDTO);
    }
    @Override
    public Cliente atualizarCliente(Long id, ClienteDTO clienteDTO) {

        validaCamposObrigatorios(clienteDTO);

        Cliente atualizaCliente = validaClienteID(id);
        atualizaCliente.setNome(clienteDTO.getNome());

        atualizaCliente.getEndereco().setCep(clienteDTO.getCep());
        atualizaCliente.getEndereco().setNumero(clienteDTO.getNumero());
        atualizaCliente.getEndereco().setComplemento(clienteDTO.getComplemento());


        return clienteRepository.save(atualizaCliente);
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.delete(validaClienteID(id));
    }

    private Cliente salvarClienteComCep(ClienteDTO clienteDTO) {
        validaCamposObrigatorios(clienteDTO);
        Endereco end = existsEndereco(clienteDTO).orElseGet(() -> salvarEndereco(clienteDTO));
        return clienteRepository.save(new Cliente(clienteDTO.getNome(), end));
    }

    private Optional<Endereco> existsEndereco(ClienteDTO clienteDTO) {
        validaCEP(clienteDTO);
        return Optional.ofNullable(enderecoRepository.existsEndereco(clienteDTO.getCep(),
                clienteDTO.getNumero(),
                clienteDTO.getComplemento()));
    }

    private Endereco salvarEndereco(ClienteDTO clienteDTO) {
        Endereco endEntity = new Endereco(viaCepService.consultarCep(clienteDTO.getCep()).orElseThrow(RecursoNaoEncontrado::new));
        endEntity.setNumero(clienteDTO.getNumero());
        endEntity.setComplemento(clienteDTO.getComplemento());

        return enderecoRepository.save(endEntity);
    }

    private void validaCEP(ClienteDTO clienteDTO) {
        if (!clienteDTO.getCep().substring(5, 6).equalsIgnoreCase("-")) {
            String cep = clienteDTO.getCep();
            clienteDTO.setCep(cep.substring(0, 5).concat("-").concat(cep.substring(5, 8)));
        }
    }

    private void validaCamposObrigatorios(ClienteDTO clienteDTO) {
        if (clienteDTO.getCep() == null)
            throw new CampoObrigatorioException("CEP");
        else if (clienteDTO.getNome() == null)
            throw new CampoObrigatorioException("Nome");
        else if (clienteDTO.getNumero() == null)
            throw new CampoObrigatorioException("Número");
        else if (clienteDTO.getComplemento() == null)
            throw new CampoObrigatorioException("Complemento");
    }

    private Cliente validaClienteID(Long id) {
        return clienteRepository.findById(id).orElseThrow(RecursoNaoEncontrado::new);
    }
}
