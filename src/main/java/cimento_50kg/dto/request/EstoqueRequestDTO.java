package cimento_50kg.dto.request;

import java.time.LocalDate;

public class EstoqueRequestDTO {
    public int quantidadeDisponivel;
    public LocalDate dataAtualizacao;
    public String localArmazenamento;
    public Long cimentoId;
}
