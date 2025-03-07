package com.stefanini.visitas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefanini.visitas.modelo.Ingreso;
import com.stefanini.visitas.repository.IngresoRepository;
import com.stefanini.visitas.service.IngresoService;

@Service
public class IngresoServiceImpl implements IngresoService {
	
	@Autowired
    private IngresoRepository ingresoRepository;

    @Override
    public List<Ingreso> obtenerTodosLosUsuarios() {
        return ingresoRepository.findAll();
    }
}
