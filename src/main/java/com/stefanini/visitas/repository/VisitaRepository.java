package com.stefanini.visitas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stefanini.visitas.modelo.Visita;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Integer> {
	List<Visita> findByUser(String user);
}
