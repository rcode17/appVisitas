package com.stefanini.visitas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stefanini.visitas.modelo.VisitaServicio;

import java.util.List;

@Repository
public interface VisitaServicioRepository extends JpaRepository<VisitaServicio, Integer> {
    
    List<VisitaServicio> findByVisitaIdVisita(Integer idVisita);
    
    boolean existsByVisitaIdVisita(Integer id);
}

