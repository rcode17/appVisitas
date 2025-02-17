package com.stefanini.visitas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stefanini.visitas.modelo.TipoVisita;

@Repository
public interface TipoVisitaRepository extends JpaRepository<TipoVisita, Short> {
}
