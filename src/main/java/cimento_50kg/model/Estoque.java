package cimento_50kg.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantidadeDisponivel;
    private LocalDate dataAtualizacao;
    private String localArmazenamento;

    @ManyToOne
    @JoinColumn(name = "cimento_id")
    private Cimento50kg cimento;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getQuantidadeDisponivel() { return quantidadeDisponivel; }
    public void setQuantidadeDisponivel(int quantidadeDisponivel) { this.quantidadeDisponivel = quantidadeDisponivel; }

    public LocalDate getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDate dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }

    public String getLocalArmazenamento() { return localArmazenamento; }
    public void setLocalArmazenamento(String localArmazenamento) { this.localArmazenamento = localArmazenamento; }

    public Cimento50kg getCimento() { return cimento; }
    public void setCimento(Cimento50kg cimento) { this.cimento = cimento; }
}
