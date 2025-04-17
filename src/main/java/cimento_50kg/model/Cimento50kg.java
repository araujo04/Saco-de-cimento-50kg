package cimento_50kg.model;

import cimento_50kg.enums.TipoDeUso;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Cimento50kg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private TipoDeUso tipoDeUso;

    private double pesoKg;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    public Long getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public TipoDeUso getTipoDeUso() {
        return tipoDeUso;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setTipoDeUso(TipoDeUso tipoDeUso) {
        this.tipoDeUso = tipoDeUso;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}