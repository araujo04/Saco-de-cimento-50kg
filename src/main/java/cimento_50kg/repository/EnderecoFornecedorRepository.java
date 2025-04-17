package cimento_50kg.repository;

import cimento_50kg.model.EnderecoFornecedor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoFornecedorRepository implements PanacheRepository<EnderecoFornecedor> {
}