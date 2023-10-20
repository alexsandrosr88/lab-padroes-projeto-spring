package com.dio.gof.service.impl;

import com.dio.gof.dto.ClienteRequestDTO;
import com.dio.gof.dto.ClienteResponseDTO;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<ClienteResponseDTO> buscarTodos() {
        return clienteRepository.findAll().stream().map(ClienteResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public ClienteResponseDTO buscarPorId(Long id) {
        validaClienteID(id);
        return new ClienteResponseDTO(clienteRepository.findById(id).get());
    }

    @Transactional(readOnly = false)
    @Override
    public ClienteResponseDTO salvarClienteComEndereco(ClienteRequestDTO clienteRequestDTO) {
        validaCamposObrigatorios(clienteRequestDTO);
        Endereco end = existsEndereco(clienteRequestDTO).orElseGet(() -> salvarEndereco(clienteRequestDTO));

        return new ClienteResponseDTO(clienteRepository.save(new Cliente(clienteRequestDTO.getNome(), end)));
    }

    @Transactional(readOnly = false)
    @Override
    public ClienteResponseDTO atualizarClienteComEndereco(Long id, ClienteRequestDTO clienteRequestDTO) {

        validaCamposObrigatorios(clienteRequestDTO);

        Cliente atualizaCliente = validaClienteID(id);
        atualizaCliente.setNome(clienteRequestDTO.getNome());

        Endereco end = existsEndereco(clienteRequestDTO).orElseGet(() -> salvarEndereco(clienteRequestDTO));

        atualizaCliente.setEndereco(end);

        return new ClienteResponseDTO(clienteRepository.save(atualizaCliente));
    }

    @Transactional(readOnly = false)
    @Override
    public void deletarCliente(Long id) {
        clienteRepository.delete(validaClienteID(id));
    }

    private Optional<Endereco> existsEndereco(ClienteRequestDTO clienteRequestDTO) {
        validaCEP(clienteRequestDTO);
        return Optional.ofNullable(enderecoRepository.existsEndereco(clienteRequestDTO.getCep(),
                clienteRequestDTO.getNumero(),
                clienteRequestDTO.getComplemento()));
    }

    private Endereco salvarEndereco(ClienteRequestDTO clienteRequestDTO) {
        Endereco endEntity = new Endereco(viaCepService.consultarCep(clienteRequestDTO.getCep()));
        endEntity.setNumero(clienteRequestDTO.getNumero());
        endEntity.setComplemento(clienteRequestDTO.getComplemento());

        return enderecoRepository.save(endEntity);
    }

    private void validaCEP(ClienteRequestDTO clienteRequestDTO) {
        if (!clienteRequestDTO.getCep().substring(5, 6).equalsIgnoreCase("-")) {
            String cep = clienteRequestDTO.getCep();
            clienteRequestDTO.setCep(cep.substring(0, 5).concat("-").concat(cep.substring(5, 8)));
        }
    }

    private void validaCamposObrigatorios(ClienteRequestDTO clienteRequestDTO) {
        if (clienteRequestDTO.getCep() == null || clienteRequestDTO.getCep().equalsIgnoreCase("string"))
            throw new CampoObrigatorioException("CEP");
        else if (clienteRequestDTO.getNome() == null)
            throw new CampoObrigatorioException("Nome");
        else if (clienteRequestDTO.getNumero() == null)
            throw new CampoObrigatorioException("Número");
        else if (clienteRequestDTO.getComplemento() == null)
            throw new CampoObrigatorioException("Complemento");
    }

    private Cliente validaClienteID(Long id) {
        return clienteRepository.findById(id).orElseThrow(RecursoNaoEncontrado::new);
    }
}
