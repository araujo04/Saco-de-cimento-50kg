package cimento_50kg.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NotaFiscalResponseDTO {
    public Long id;
    public String numeroNota;
    public LocalDate dataEmissao;
    public BigDecimal valorTotal;
    public Long pedidoId;
}
