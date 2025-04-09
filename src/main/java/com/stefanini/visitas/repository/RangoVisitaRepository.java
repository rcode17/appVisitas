package com.stefanini.visitas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stefanini.visitas.modelo.RangoVisita;

@Repository
public interface RangoVisitaRepository extends JpaRepository<RangoVisita, Short> {
	
	 List<RangoVisita> findAllByOrderByNombreAsc();
}
