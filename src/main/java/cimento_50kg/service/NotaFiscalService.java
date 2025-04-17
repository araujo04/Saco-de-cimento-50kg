package cimento_50kg.service;

import cimento_50kg.dto.request.NotaFiscalRequestDTO;
import cimento_50kg.dto.response.NotaFiscalResponseDTO;
import cimento_50kg.model.NotaFiscal;
import cimento_50kg.model.Pedido;
import cimento_50kg.repository.NotaFiscalRepository;
import cimento_50kg.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class NotaFiscalService {

    private final NotaFiscalRepository repository;
    private final PedidoRepository pedidoRepository;

    public NotaFiscalService(NotaFiscalRepository repository, PedidoRepository pedidoRepository) {
        this.repository = repository;
        this.pedidoRepository = pedidoRepository;
    }

    public List<NotaFiscalResponseDTO> listarTodos() {
        return repository.listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public NotaFiscalResponseDTO buscarPorId(Long id) {
        NotaFiscal nf = repository.findById(id);
        if (nf == null) throw new NotFoundException();
        return toDTO(nf);
    }

    @Transactional
    public void inserir(NotaFiscalRequestDTO dto) {
        NotaFiscal nf = new NotaFiscal();
        fromDTO(nf, dto);
        repository.persist(nf);
    }

    @Transactional
    public void atualizar(Long id, NotaFiscalRequestDTO dto) {
        NotaFiscal nf = repository.findById(id);
        if (nf == null) throw new NotFoundException();
        fromDTO(nf, dto);
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private void fromDTO(NotaFiscal nf, NotaFiscalRequestDTO dto) {
        nf.setNumeroNota(dto.numeroNota);
        nf.setDataEmissao(dto.dataEmissao);
        nf.setValorTotal(dto.valorTotal);
        Pedido pedido = pedidoRepository.findById(dto.pedidoId);
        if (pedido == null) throw new NotFoundException("Pedido não encontrado");
        nf.setPedido(pedido);
    }

    private NotaFiscalResponseDTO toDTO(NotaFiscal nf) {
        NotaFiscalResponseDTO dto = new NotaFiscalResponseDTO();
        dto.id = nf.getId();
        dto.numeroNota = nf.getNumeroNota();
        dto.dataEmissao = nf.getDataEmissao();
        dto.valorTotal = nf.getValorTotal();
        dto.pedidoId = nf.getPedido().getId();
        return dto;
    }
}
