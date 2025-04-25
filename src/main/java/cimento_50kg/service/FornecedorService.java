package cimento_50kg.service;

import cimento_50kg.dto.request.FornecedorRequestDTO;
import cimento_50kg.dto.response.FornecedorResponseDTO;
import cimento_50kg.model.Fornecedor;
import cimento_50kg.repository.FornecedorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class FornecedorService {

    private final FornecedorRepository repository;

    public FornecedorService(FornecedorRepository repository) {
        this.repository = repository;
    }

    public List<FornecedorResponseDTO> listarTodos() {
        return repository.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public FornecedorResponseDTO buscarPorId(Long id) {
        var entity = repository.findById(id);
        return entity != null ? toDTO(entity) : null;
    }

    @Transactional
    public FornecedorResponseDTO criar(FornecedorRequestDTO dto) {
        var entity = new Fornecedor();
        entity.setNome(dto.nome);
        repository.persist(entity);
        return toDTO(entity);
    }

    @Transactional
    public FornecedorResponseDTO atualizar(Long id, FornecedorRequestDTO dto) {
        var entity = repository.findById(id);
        if (entity == null) return null;
        entity.setNome(dto.nome);
        return toDTO(entity);
    }

    @Transactional
    public boolean deletar(Long id) {
        return repository.deleteById(id);
    }

    private FornecedorResponseDTO toDTO(Fornecedor entity) {
        var dto = new FornecedorResponseDTO();
        dto.id = entity.getId();
        dto.nome = entity.getNome();
        return dto;
    }
}
