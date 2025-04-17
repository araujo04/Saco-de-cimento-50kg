package cimento_50kg.service;

import cimento_50kg.dto.request.Cimento50kgRequestDTO;
import cimento_50kg.dto.response.Cimento50kgResponseDTO;
import cimento_50kg.model.Cimento50kg;
import cimento_50kg.model.Fornecedor;
import cimento_50kg.repository.Cimento50kgRepository;
import cimento_50kg.repository.FornecedorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class Cimento50kgService {

    private final Cimento50kgRepository repository;
    private final FornecedorRepository fornecedorRepository;

    public Cimento50kgService(Cimento50kgRepository repository, FornecedorRepository fornecedorRepository) {
        this.repository = repository;
        this.fornecedorRepository = fornecedorRepository;
    }

    public List<Cimento50kgResponseDTO> listarTodos() {
        return repository.listAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public Cimento50kgResponseDTO buscarPorId(Long id) {
        Cimento50kg entidade = repository.findById(id);
        return entidade != null ? toResponseDTO(entidade) : null;
    }

    public List<Cimento50kgResponseDTO> buscarPorMarca(String marca) {
        return repository.find("marca", marca).stream()
                .map(e -> toResponseDTO((Cimento50kg) e))
                .collect(Collectors.toList());
    }

    @Transactional
    public Cimento50kgResponseDTO criar(Cimento50kgRequestDTO dto) {
        Cimento50kg entidade = new Cimento50kg();
        fromRequestDTO(dto, entidade);
        repository.persist(entidade);
        return toResponseDTO(entidade);
    }

    @Transactional
    public Cimento50kgResponseDTO atualizar(Long id, Cimento50kgRequestDTO dto) {
        Cimento50kg entidade = repository.findById(id);
        if (entidade == null) return null;
        fromRequestDTO(dto, entidade);
        return toResponseDTO(entidade);
    }

    @Transactional
    public boolean deletar(Long id) {
        return repository.deleteById(id);
    }

    private Cimento50kgResponseDTO toResponseDTO(Cimento50kg entidade) {
        Cimento50kgResponseDTO dto = new Cimento50kgResponseDTO();
        dto.id = entidade.getId();
        dto.marca = entidade.getMarca();
        dto.preco = entidade.getPreco();
        dto.tipoDeUso = entidade.getTipoDeUso();
        dto.pesoKg = entidade.getPesoKg();
        dto.fornecedorNome = entidade.getFornecedor() != null ? entidade.getFornecedor().getNome() : null;
        return dto;
    }

    private void fromRequestDTO(Cimento50kgRequestDTO dto, Cimento50kg entidade) {
        entidade.setMarca(dto.marca);
        entidade.setPreco(dto.preco);
        entidade.setTipoDeUso(dto.tipoDeUso);
        entidade.setPesoKg(dto.pesoKg);

        if (dto.fornecedorId != null) {
            Fornecedor fornecedor = fornecedorRepository.findById(dto.fornecedorId);
            entidade.setFornecedor(fornecedor);
        }
    }
}