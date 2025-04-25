package cimento_50kg.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PagamentoRequestDTO {
    public String formaPagamento;
    public BigDecimal valorPago;
    public LocalDate dataPagamento;
    public String status;
    public Long pedidoId;
}
