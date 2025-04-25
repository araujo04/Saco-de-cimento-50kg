package cimento_50kg.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PagamentoResponseDTO {
    public Long id;
    public String formaPagamento;
    public BigDecimal valorPago;
    public LocalDate dataPagamento;
    public String status;
    public Long pedidoId;
}
