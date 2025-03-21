package com.stefanini.visitas.controller;

import com.stefanini.visitas.modelo.TipoVisita;
import com.stefanini.visitas.service.TipoVisitaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/tipo-visita")
public class TipoVisitaController {

    @Autowired
    private TipoVisitaService tipoVisitaService;

    @GetMapping
    public ResponseEntity<List<TipoVisita>> obtenerTodasLasTipoVisitas() {
        List<TipoVisita> visitas = tipoVisitaService.obtenerTodasLasTipoVisitas();
        return ResponseEntity.ok(visitas);
    }
}