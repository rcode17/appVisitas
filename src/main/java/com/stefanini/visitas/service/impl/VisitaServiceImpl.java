package com.stefanini.visitas.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefanini.visitas.dto.VisitaDTO;
import com.stefanini.visitas.dto.VisitaServicioDTO;
import com.stefanini.visitas.modelo.RangoVisita;
import com.stefanini.visitas.modelo.TipoVisita;
import com.stefanini.visitas.modelo.Visita;
import com.stefanini.visitas.modelo.VisitaServicio;
import com.stefanini.visitas.repository.RangoVisitaRepository;
import com.stefanini.visitas.repository.TipoVisitaRepository;
import com.stefanini.visitas.repository.VisitaRepository;
import com.stefanini.visitas.repository.VisitaServicioRepository;
import com.stefanini.visitas.service.VisitaService;

@Service
public class VisitaServiceImpl implements VisitaService{
	
	@Autowired
	private VisitaRepository visitaRepository;
	
	@Autowired
	private VisitaServicioRepository visitaServicioRepository;
	
	@Autowired
	private TipoVisitaRepository tipoVisitaRepository;
	
	@Autowired
    private RangoVisitaRepository rangoVisitaRepository;

	@Override
	public List<Visita> obtenerTodasLasVisitas() {
		return visitaRepository.findAll();
	}

	@Override
	public Visita obtenerPorId(Integer id) {
	    return visitaRepository.findById(id)
	        .orElseThrow(() -> new IllegalArgumentException("No se encontró la visita con ID: " + id));
	}

	@Transactional
    @Override
    public Visita guardarVisita(VisitaDTO visitaDTO) {
        Visita visita = new Visita();
        visita.setUser(visitaDTO.getUser());
        visita.setCodCliente(visitaDTO.getCodCliente());
        visita.setCodProyecto(visitaDTO.getCodProyecto());
        visita.setDiaVisita(visitaDTO.getDiaVisita());
        visita.setModalidad(visitaDTO.getModalidad());
        visita.setNombreVisitada(visitaDTO.getNombreVisitada());
        visita.setEmailVisitada(visitaDTO.getEmailVisitada());
        visita.setGeneraOportunidad(visitaDTO.getGeneraOportunidad());
        visita.setEsCliente(visitaDTO.getEsCliente());
        visita.setObservaciones(visitaDTO.getObservaciones());

        TipoVisita tipoVisita = tipoVisitaRepository.findById(visitaDTO.getIdTipoVisita())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de visita no encontrado"));
        visita.setTipoVisita(tipoVisita);

        RangoVisita rangoVisita = rangoVisitaRepository.findById(visitaDTO.getIdRango())
                .orElseThrow(() -> new IllegalArgumentException("Rango de visita no encontrado"));
        visita.setRangoVisita(rangoVisita);

        if (visitaDTO.getServicios() != null && !visitaDTO.getServicios().isEmpty()) {
            List<VisitaServicio> servicios = new ArrayList<>();
            for (VisitaServicioDTO servicioDTO : visitaDTO.getServicios()) {
                VisitaServicio servicio = new VisitaServicio();
                servicio.setTipoPersona(servicioDTO.getTipoPersona());
                servicio.setNombrePersona(servicioDTO.getNombrePersona());
                servicio.setEmailPersona(servicioDTO.getEmailPersona());
                servicio.setObservaciones(servicioDTO.getObservaciones());
                servicio.setEsAlerta(servicioDTO.getEsAlerta());
                servicio.setVisita(visita);
                servicios.add(servicio);
            }
            visita.setServicios(servicios);
        }

        return visitaRepository.save(visita);
    }

	@Override
	@Transactional
	public Visita editarVisita(Integer id, VisitaDTO visitaDTO) {
	    Visita visitaExistente = visitaRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("No se encontró la visita con ID: " + id));

	    visitaExistente.setUser(visitaDTO.getUser());
	    visitaExistente.setCodCliente(visitaDTO.getCodCliente());
	    visitaExistente.setCodProyecto(visitaDTO.getCodProyecto());
	    visitaExistente.setDiaVisita(visitaDTO.getDiaVisita());
	    visitaExistente.setModalidad(visitaDTO.getModalidad());
	    visitaExistente.setNombreVisitada(visitaDTO.getNombreVisitada());
	    visitaExistente.setEmailVisitada(visitaDTO.getEmailVisitada());
	    visitaExistente.setGeneraOportunidad(visitaDTO.getGeneraOportunidad());
	    visitaExistente.setEsCliente(visitaDTO.getEsCliente());
	    visitaExistente.setObservaciones(visitaDTO.getObservaciones());

	    TipoVisita tipoVisita = tipoVisitaRepository.findById(visitaDTO.getIdTipoVisita())
	            .orElseThrow(() -> new IllegalArgumentException("Tipo de visita no encontrado"));
	    visitaExistente.setTipoVisita(tipoVisita);

	    RangoVisita rangoVisita = rangoVisitaRepository.findById(visitaDTO.getIdRango())
	            .orElseThrow(() -> new IllegalArgumentException("Rango de visita no encontrado"));
	    visitaExistente.setRangoVisita(rangoVisita);

	    if (visitaDTO.getServicios() != null && !visitaDTO.getServicios().isEmpty()) {
	        List<VisitaServicio> servicios = new ArrayList<>();
	        for (VisitaServicioDTO servicioDTO : visitaDTO.getServicios()) {
	            VisitaServicio servicio = new VisitaServicio();
	            servicio.setTipoPersona(servicioDTO.getTipoPersona());
	            servicio.setNombrePersona(servicioDTO.getNombrePersona());
	            servicio.setEmailPersona(servicioDTO.getEmailPersona());
	            servicio.setObservaciones(servicioDTO.getObservaciones());
	            servicio.setEsAlerta(servicioDTO.getEsAlerta());
	            servicio.setVisita(visitaExistente);
	            servicios.add(servicio);
	        }
	        visitaExistente.setServicios(servicios);
	    }

	    return visitaRepository.save(visitaExistente);
	}


    @Transactional
    @Override
    public void eliminarVisita(Integer id) {
        if (!visitaRepository.existsById(id)) {
            throw new IllegalArgumentException("La visita con ID " + id + " no existe.");
        }
        boolean tienePersonas = visitaServicioRepository.existsByVisitaIdVisita(id);
        if (tienePersonas) {
            throw new IllegalArgumentException("No se puede eliminar la visita porque tiene personas asociadas.");
        }

        visitaRepository.deleteById(id);
    }


	@Override
	public List<Visita> findByUser(String user) {
		List<Visita> visitas = visitaRepository.findByUser(user);
        if (visitas.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron visitas para el usuario con correo: " + user);
        }
        return visitas;
	}


}
