package com.stefanini.visitas.service;

import java.util.List;

import com.stefanini.visitas.modelo.Masa;

public interface MasaService {
	
	List<Masa> obtenerPorUsuario(String usuario);
	List<Masa> obtenerTodosUsuario();

}
