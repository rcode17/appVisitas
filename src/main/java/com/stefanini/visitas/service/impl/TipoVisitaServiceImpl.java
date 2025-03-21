package com.stefanini.visitas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefanini.visitas.modelo.TipoVisita;
import com.stefanini.visitas.repository.TipoVisitaRepository;
import com.stefanini.visitas.service.TipoVisitaService;

@Service
public class TipoVisitaServiceImpl implements TipoVisitaService  {
	

    @Autowired
    private TipoVisitaRepository tipoVisitaRepository;

	@Override
	public List<TipoVisita> obtenerTodasLasTipoVisitas() {
		return tipoVisitaRepository.findAllByOrderByNombreAsc(); 
	}

}
