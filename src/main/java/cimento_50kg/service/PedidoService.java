package cimento_50kg.service;

import cimento_50kg.dto.request.PedidoRequestDTO;
import cimento_50kg.dto.response.PedidoResponseDTO;
import cimento_50kg.model.Cliente;
import cimento_50kg.model.Pedido;
import cimento_50kg.repository.ClienteRepository;
import cimento_50kg.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PedidoService {

    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository repository, ClienteRepository clienteRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
    }

    public List<PedidoResponseDTO> listarTodos() {
        return repository.listAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public PedidoResponseDTO buscarPorId(Long id) {
        var entity = repository.findById(id);
        return entity != null ? toResponse(entity) : null;
    }

    @Transactional
    public PedidoResponseDTO criar(PedidoRequestDTO dto) {
        var entity = new Pedido();
        entity.setData(dto.data);
        Cliente cliente = clienteRepository.findById(dto.clienteId);
        entity.setCliente(cliente);
        entity.setObservacao(dto.observacao);
        repository.persist(entity);
        return toResponse(entity);
    }

    @Transactional
    public PedidoResponseDTO atualizar(Long id, PedidoRequestDTO dto) {
        var entity = repository.findById(id);
        if (entity == null) return null;
        entity.setData(dto.data);
        entity.setCliente(clienteRepository.findById(dto.clienteId));
        entity.setObservacao(dto.observacao);
        return toResponse(entity);
    }

    @Transactional
    public boolean deletar(Long id) {
        return repository.deleteById(id);
    }

    private PedidoResponseDTO toResponse(Pedido entity) {
        var dto = new PedidoResponseDTO();
        dto.id = entity.getId();
        dto.data = entity.getData();
        dto.clienteNome = entity.getCliente() != null ? entity.getCliente().getNome() : null;
        dto.observacao = entity.getObservacao();
        return dto;
    }
}
