package cimento_50kg.service;

import cimento_50kg.dto.request.TransportadoraRequestDTO;
import cimento_50kg.dto.response.TransportadoraResponseDTO;
import cimento_50kg.model.Transportadora;
import cimento_50kg.repository.TransportadoraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TransportadoraService {

    private final TransportadoraRepository repository;

    public TransportadoraService(TransportadoraRepository repository) {
        this.repository = repository;
    }

    public List<TransportadoraResponseDTO> listarTodos() {
        return repository.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public TransportadoraResponseDTO buscarPorId(Long id) {
        Transportadora t = repository.findById(id);
        if (t == null) throw new NotFoundException();
        return toDTO(t);
    }

    @Transactional
    public void inserir(TransportadoraRequestDTO dto) {
        Transportadora t = new Transportadora();
        fromDTO(t, dto);
        repository.persist(t);
    }

    @Transactional
    public void atualizar(Long id, TransportadoraRequestDTO dto) {
        Transportadora t = repository.findById(id);
        if (t == null) throw new NotFoundException();
        fromDTO(t, dto);
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private void fromDTO(Transportadora t, TransportadoraRequestDTO dto) {
        t.setNome(dto.nome);
        t.setCnpj(dto.cnpj);
        t.setTelefone(dto.telefone);
        t.setEmail(dto.email);
    }

    private TransportadoraResponseDTO toDTO(Transportadora t) {
        TransportadoraResponseDTO dto = new TransportadoraResponseDTO();
        dto.id = t.getId();
        dto.nome = t.getNome();
        dto.cnpj = t.getCnpj();
        dto.telefone = t.getTelefone();
        dto.email = t.getEmail();
        return dto;
    }
}
