package cimento_50kg.service;

import cimento_50kg.dto.request.ClienteRequestDTO;
import cimento_50kg.dto.response.ClienteResponseDTO;
import cimento_50kg.model.Cliente;
import cimento_50kg.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<ClienteResponseDTO> listarTodos() {
        return repository.listAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = repository.findById(id);
        return cliente != null ? toResponseDTO(cliente) : null;
    }

    @Transactional
    public ClienteResponseDTO criar(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome);
        cliente.setEmail(dto.email);
        cliente.setTelefone(dto.telefone);
        repository.persist(cliente);
        return toResponseDTO(cliente);
    }

    @Transactional
    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {
        Cliente cliente = repository.findById(id);
        if (cliente == null) return null;
        cliente.setNome(dto.nome);
        cliente.setEmail(dto.email);
        cliente.setTelefone(dto.telefone);
        return toResponseDTO(cliente);
    }

    @Transactional
    public boolean deletar(Long id) {
        return repository.deleteById(id);
    }

    private ClienteResponseDTO toResponseDTO(Cliente cliente) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.id = cliente.getId();
        dto.nome = cliente.getNome();
        dto.email = cliente.getEmail();
        dto.telefone = cliente.getTelefone();
        return dto;
    }
}