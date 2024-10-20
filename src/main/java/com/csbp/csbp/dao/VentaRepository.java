package com.csbp.csbp.dao;

import com.csbp.csbp.domain.Venta;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VentaRepository extends CrudRepository<Venta, Long> {
    Venta findFirstByOrderByIdDesc();
    Optional<Venta> findByCodigo(String codigo);
}
