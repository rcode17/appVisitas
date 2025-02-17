package com.stefanini.visitas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefanini.visitas.modelo.Masa;
import com.stefanini.visitas.repository.MasaRepository;
import com.stefanini.visitas.service.MasaService;

@Service
public class MasaServiceImpl implements MasaService {
	
	@Autowired
	private MasaRepository masaRepository;

	@Override
	public List<Masa> obtenerPorUsuario(String usuario) {
		List<Masa> masa = masaRepository.findByUser(usuario);
		if (masa.isEmpty()) {
	        throw new IllegalArgumentException("No se encontraron registros para el usuario: " + usuario);
	    }
		return masa;
	}

}
