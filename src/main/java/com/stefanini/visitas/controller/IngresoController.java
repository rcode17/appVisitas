package com.stefanini.visitas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefanini.visitas.modelo.Ingreso;
import com.stefanini.visitas.service.IngresoService;

@RestController
@RequestMapping("/api/ingreso")
public class IngresoController {
	
	@Autowired
    private IngresoService ingresoService;

    @GetMapping
    public List<Ingreso> getUser() {
        return ingresoService.obtenerTodosLosUsuarios();
    }
}
