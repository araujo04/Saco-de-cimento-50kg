package cimento_50kg.service;

import cimento_50kg.dto.request.PagamentoRequestDTO;
import cimento_50kg.dto.response.PagamentoResponseDTO;
import cimento_50kg.model.Pagamento;
import cimento_50kg.model.Pedido;
import cimento_50kg.repository.PagamentoRepository;
import cimento_50kg.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PagamentoService {

    private final PagamentoRepository repository;
    private final PedidoRepository pedidoRepository;

    public PagamentoService(PagamentoRepository repository, PedidoRepository pedidoRepository) {
        this.repository = repository;
        this.pedidoRepository = pedidoRepository;
    }

    public List<PagamentoResponseDTO> listarTodos() {
        return repository.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PagamentoResponseDTO buscarPorId(Long id) {
        Pagamento pagamento = repository.findById(id);
        if (pagamento == null) throw new NotFoundException();
        return toDTO(pagamento);
    }

    @Transactional
    public PagamentoResponseDTO criar(PagamentoRequestDTO dto) {
        Pagamento pagamento = new Pagamento();
        fromDTO(pagamento, dto);
        repository.persist(pagamento);
        return toDTO(pagamento);
    }

    @Transactional
    public PagamentoResponseDTO atualizar(Long id, PagamentoRequestDTO dto) {
        Pagamento pagamento = repository.findById(id);
        if (pagamento == null) throw new NotFoundException();
        fromDTO(pagamento, dto);
        return toDTO(pagamento);
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private void fromDTO(Pagamento pagamento, PagamentoRequestDTO dto) {
        pagamento.setFormaPagamento(dto.formaPagamento);
        pagamento.setValorPago(dto.valorPago);
        pagamento.setDataPagamento(dto.dataPagamento);
        pagamento.setStatus(dto.status);
        Pedido pedido = pedidoRepository.findById(dto.pedidoId);
        if (pedido == null) throw new NotFoundException("Pedido não encontrado");
        pagamento.setPedido(pedido);
    }

    private PagamentoResponseDTO toDTO(Pagamento pagamento) {
        PagamentoResponseDTO dto = new PagamentoResponseDTO();
        dto.id = pagamento.getId();
        dto.formaPagamento = pagamento.getFormaPagamento();
        dto.valorPago = pagamento.getValorPago();
        dto.dataPagamento = pagamento.getDataPagamento();
        dto.status = pagamento.getStatus();
        dto.pedidoId = pagamento.getPedido().getId();
        return dto;
    }
}
