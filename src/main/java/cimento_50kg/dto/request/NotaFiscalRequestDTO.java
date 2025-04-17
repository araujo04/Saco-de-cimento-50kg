package cimento_50kg.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NotaFiscalRequestDTO {
    public String numeroNota;
    public LocalDate dataEmissao;
    public BigDecimal valorTotal;
    public Long pedidoId;
}
