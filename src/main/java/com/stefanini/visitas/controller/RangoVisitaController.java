package com.stefanini.visitas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefanini.visitas.modelo.RangoVisita;
import com.stefanini.visitas.service.RangoVisitaService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/rangos-visita")
public class RangoVisitaController {
	

    @Autowired
    private RangoVisitaService rangoVisitaService;
	
    @GetMapping
    public List<RangoVisita> getRangosVisita() {
        return rangoVisitaService.findAllByOrderByNombreAsc(); // Devuelve los rangos de visita ordenados
    }

}
