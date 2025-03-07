package com.stefanini.visitas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stefanini.visitas.modelo.Ingreso;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Integer> {
}
