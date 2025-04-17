package cimento_50kg.dto.request;

import cimento_50kg.enums.TipoDeUso;
import java.math.BigDecimal;

public class Cimento50kgRequestDTO {

    public String marca;
    public BigDecimal preco;
    public TipoDeUso tipoDeUso;
    public double pesoKg;
    public Long fornecedorId; 
}