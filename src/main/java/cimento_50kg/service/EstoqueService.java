package cimento_50kg.service;

import cimento_50kg.dto.request.EstoqueRequestDTO;
import cimento_50kg.dto.response.EstoqueResponseDTO;
import cimento_50kg.model.Cimento50kg;
import cimento_50kg.model.Estoque;
import cimento_50kg.repository.Cimento50kgRepository;
import cimento_50kg.repository.EstoqueRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EstoqueService {

    private final EstoqueRepository repository;
    private final Cimento50kgRepository cimentoRepository;

    public EstoqueService(EstoqueRepository repository, Cimento50kgRepository cimentoRepository) {
        this.repository = repository;
        this.cimentoRepository = cimentoRepository;
    }

    public List<EstoqueResponseDTO> listarTodos() {
        return repository.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public EstoqueResponseDTO buscarPorId(Long id) {
        Estoque e = repository.findById(id);
        if (e == null) throw new NotFoundException();
        return toDTO(e);
    }

    @Transactional
    public EstoqueResponseDTO criar(EstoqueRequestDTO dto) {
        Estoque e = new Estoque();
        fromDTO(e, dto);
        repository.persist(e);
        return toDTO(e);
    }

    @Transactional
    public EstoqueResponseDTO atualizar(Long id, EstoqueRequestDTO dto) {
        Estoque e = repository.findById(id);
        if (e == null) throw new NotFoundException();
        fromDTO(e, dto);
        return toDTO(e);
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private void fromDTO(Estoque e, EstoqueRequestDTO dto) {
        e.setQuantidadeDisponivel(dto.quantidadeDisponivel);
        e.setDataAtualizacao(dto.dataAtualizacao);
        e.setLocalArmazenamento(dto.localArmazenamento);
        Cimento50kg cimento = cimentoRepository.findById(dto.cimentoId);
        if (cimento == null) throw new NotFoundException("Cimento não encontrado");
        e.setCimento(cimento);
    }

    private EstoqueResponseDTO toDTO(Estoque e) {
        EstoqueResponseDTO dto = new EstoqueResponseDTO();
        dto.id = e.getId();
        dto.quantidadeDisponivel = e.getQuantidadeDisponivel();
        dto.dataAtualizacao = e.getDataAtualizacao();
        dto.localArmazenamento = e.getLocalArmazenamento();
        dto.cimentoMarca = e.getCimento() != null ? e.getCimento().getMarca() : null;
        return dto;
    }
}
