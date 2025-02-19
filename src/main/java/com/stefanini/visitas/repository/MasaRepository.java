package com.stefanini.visitas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stefanini.visitas.modelo.Masa;

@Repository
public interface MasaRepository extends JpaRepository<Masa, Long> {
    
    List<Masa> findByUser(String user);
    List<Masa> findAll();
}
