package com.stefanini.visitas.service;

import java.util.List;

import com.stefanini.visitas.dto.VisitaServicioDTO;
import com.stefanini.visitas.modelo.VisitaServicio;

public interface VisitaServicioService {
	
	public List<VisitaServicio> obtenerServiciosPorVisita(Integer idVisita);
	
	VisitaServicio agregarPersona(Integer idVisita, VisitaServicioDTO visitaServicioDTO);
	
	VisitaServicio editarPersona(Integer idVisitaServicio, Integer idVisita, VisitaServicioDTO visitaServicioDTO);
	
	void eliminarPersona(Integer idVisita, Integer idVisitaServicio);

}
