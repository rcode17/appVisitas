package com.stefanini.visitas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stefanini.visitas.modelo.TipoVisita;

@Repository
public interface TipoVisitaRepository extends JpaRepository<TipoVisita, Short> {
	   List<TipoVisita> findAllByOrderByNombreAsc(); 
}
