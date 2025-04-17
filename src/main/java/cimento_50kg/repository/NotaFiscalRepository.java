
package cimento_50kg.repository;

import cimento_50kg.model.NotaFiscal;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotaFiscalRepository implements PanacheRepository<NotaFiscal> {
}
