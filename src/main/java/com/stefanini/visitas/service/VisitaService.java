package com.stefanini.visitas.service;

import java.util.List;

import com.stefanini.visitas.dto.VisitaDTO;
import com.stefanini.visitas.modelo.Visita;

public interface VisitaService {
	
	List<Visita> obtenerTodasLasVisitas();
	
	Visita obtenerPorId(Integer id);
	
	public Visita guardarVisita(VisitaDTO visitaDTO);
	public Visita editarVisita(Integer id, VisitaDTO visitaDTO);
    
    void eliminarVisita(Integer id);
    
    List<Visita> findByUser(String user);

}
