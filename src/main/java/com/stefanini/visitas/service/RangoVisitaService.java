package com.stefanini.visitas.service;

import java.util.List;

import com.stefanini.visitas.modelo.RangoVisita;

public interface RangoVisitaService {
	 List<RangoVisita> findAllByOrderByNombreAsc();

}
