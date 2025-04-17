package cimento_50kg.dto.response;

import cimento_50kg.enums.TipoDeUso;
import java.math.BigDecimal;

public class Cimento50kgResponseDTO {

    public Long id;
    public String marca;
    public BigDecimal preco;
    public TipoDeUso tipoDeUso;
    public double pesoKg;
    public String fornecedorNome; 
}