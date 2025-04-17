package cimento_50kg.service;

import cimento_50kg.dto.request.EnderecoFornecedorRequestDTO;
import cimento_50kg.dto.response.EnderecoFornecedorResponseDTO;
import cimento_50kg.model.EnderecoFornecedor;
import cimento_50kg.model.Fornecedor;
import cimento_50kg.repository.EnderecoFornecedorRepository;
import cimento_50kg.repository.FornecedorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EnderecoFornecedorService {

    private final EnderecoFornecedorRepository repository;
    private final FornecedorRepository fornecedorRepository;

    public EnderecoFornecedorService(EnderecoFornecedorRepository repository, FornecedorRepository fornecedorRepository) {
        this.repository = repository;
        this.fornecedorRepository = fornecedorRepository;
    }

    public List<EnderecoFornecedorResponseDTO> listarTodos() {
        return repository.listAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public EnderecoFornecedorResponseDTO buscarPorId(Long id) {
        var entity = repository.findById(id);
        return entity != null ? toResponse(entity) : null;
    }

    @Transactional
    public EnderecoFornecedorResponseDTO criar(EnderecoFornecedorRequestDTO dto) {
        var entity = new EnderecoFornecedor();
        entity.setRua(dto.rua);
        entity.setNumero(dto.numero);
        entity.setCidade(dto.cidade);
        entity.setEstado(dto.estado);
        entity.setCep(dto.cep);
        entity.setFornecedor(fornecedorRepository.findById(dto.fornecedorId));
        repository.persist(entity);
        return toResponse(entity);
    }

    @Transactional
    public EnderecoFornecedorResponseDTO atualizar(Long id, EnderecoFornecedorRequestDTO dto) {
        var entity = repository.findById(id);
        if (entity == null) return null;
        entity.setRua(dto.rua);
        entity.setNumero(dto.numero);
        entity.setCidade(dto.cidade);
        entity.setEstado(dto.estado);
        entity.setCep(dto.cep);
        entity.setFornecedor(fornecedorRepository.findById(dto.fornecedorId));
        return toResponse(entity);
    }

    @Transactional
    public boolean deletar(Long id) {
        return repository.deleteById(id);
    }

    private EnderecoFornecedorResponseDTO toResponse(EnderecoFornecedor entity) {
        var dto = new EnderecoFornecedorResponseDTO();
        dto.id = entity.getId();
        dto.rua = entity.getRua();
        dto.numero = entity.getNumero();
        dto.cidade = entity.getCidade();
        dto.estado = entity.getEstado();
        dto.cep = entity.getCep();
        dto.fornecedorNome = entity.getFornecedor() != null ? entity.getFornecedor().getNome() : null;
        return dto;
    }
}