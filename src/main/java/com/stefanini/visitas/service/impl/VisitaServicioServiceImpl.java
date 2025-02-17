package com.stefanini.visitas.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefanini.visitas.dto.VisitaServicioDTO;
import com.stefanini.visitas.modelo.Visita;
import com.stefanini.visitas.modelo.VisitaServicio;
import com.stefanini.visitas.repository.VisitaRepository;
import com.stefanini.visitas.repository.VisitaServicioRepository;
import com.stefanini.visitas.service.VisitaServicioService;

@Service
public class VisitaServicioServiceImpl implements VisitaServicioService{
	
	@Autowired
	private VisitaServicioRepository visitaServicioRepository;
	
	@Autowired
	private VisitaRepository visitaRepository;

	@Override
	public List<VisitaServicio> obtenerServiciosPorVisita(Integer idVisita) {
	    List<VisitaServicio> servicios = visitaServicioRepository.findByVisitaIdVisita(idVisita);
	    
	    if (servicios.isEmpty()) {
	        throw new IllegalArgumentException("No se encontraron personas para la visita con ID: " + idVisita);
	    }

	    return servicios;
	}

	@Override
    @Transactional
    public VisitaServicio agregarPersona(Integer idVisita, VisitaServicioDTO visitaServicioDTO) {
		Visita visita = visitaRepository.findById(idVisita)
	            .orElseThrow(() -> new IllegalArgumentException("No se encontró la visita con ID: " + idVisita));

	    VisitaServicio visitaServicio = new VisitaServicio();
	    visitaServicio.setTipoPersona(visitaServicioDTO.getTipoPersona());
	    visitaServicio.setNombrePersona(visitaServicioDTO.getNombrePersona());
	    visitaServicio.setEmailPersona(visitaServicioDTO.getEmailPersona());
	    visitaServicio.setObservaciones(visitaServicioDTO.getObservaciones());
	    visitaServicio.setEsAlerta(visitaServicioDTO.getEsAlerta() != null ? visitaServicioDTO.getEsAlerta() : false);
	    visitaServicio.setVisita(visita);

	    return visitaServicioRepository.save(visitaServicio);
    }

	@Override
	@Transactional
	public VisitaServicio editarPersona(Integer idVisitaServicio, Integer idVisita, VisitaServicioDTO visitaServicioDTO) {
	    VisitaServicio personaExistente = visitaServicioRepository.findById(idVisitaServicio)
	        .orElseThrow(() -> new IllegalArgumentException("No se encontró la persona con ID: " + idVisitaServicio));

	    if (!personaExistente.getVisita().getIdVisita().equals(idVisita)) {
	        throw new IllegalArgumentException("Error: La persona no pertenece a la visita indicada.");
	    }

	    personaExistente.setTipoPersona(visitaServicioDTO.getTipoPersona());
	    personaExistente.setNombrePersona(visitaServicioDTO.getNombrePersona());
	    personaExistente.setEmailPersona(visitaServicioDTO.getEmailPersona());
	    personaExistente.setObservaciones(visitaServicioDTO.getObservaciones());
	    personaExistente.setEsAlerta(visitaServicioDTO.getEsAlerta() != null ? visitaServicioDTO.getEsAlerta() : false);

	    return visitaServicioRepository.save(personaExistente);
	}

	@Override
    @Transactional
    public void eliminarPersona(Integer idVisita, Integer idVisitaServicio) {
        VisitaServicio visitaServicio = visitaServicioRepository.findById(idVisitaServicio)
                .orElseThrow(() -> new IllegalArgumentException("La persona no existe"));

        if (!visitaServicio.getVisita().getIdVisita().equals(idVisita)) {
            throw new IllegalArgumentException("La persona no pertenece a la visita indicada");
        }

        visitaServicioRepository.delete(visitaServicio);
    }

}
