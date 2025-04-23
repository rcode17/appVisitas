package com.stefanini.visitas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefanini.visitas.modelo.RangoVisita;
import com.stefanini.visitas.repository.RangoVisitaRepository;
import com.stefanini.visitas.service.RangoVisitaService;

@Service
public class RangoVisitaServiceImpl implements RangoVisitaService {

	@Autowired
	private RangoVisitaRepository rangoVisitaRepository;

	@Override
	public List<RangoVisita> findAllByOrderByNombreAsc() {

		return rangoVisitaRepository.findAllByOrderByIdRangoAsc();
	}

}
