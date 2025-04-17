package cimento_50kg.repository;

import cimento_50kg.model.Cimento50kg;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Cimento50kgRepository implements PanacheRepository<Cimento50kg> {
}