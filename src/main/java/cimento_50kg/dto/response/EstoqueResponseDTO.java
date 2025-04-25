package cimento_50kg.dto.response;

import java.time.LocalDate;

public class EstoqueResponseDTO {
    public Long id;
    public int quantidadeDisponivel;
    public LocalDate dataAtualizacao;
    public String localArmazenamento;
    public String cimentoMarca;
}
