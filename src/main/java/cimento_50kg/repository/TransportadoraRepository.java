
package cimento_50kg.repository;

import cimento_50kg.model.Transportadora;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransportadoraRepository implements PanacheRepository<Transportadora> {
}
