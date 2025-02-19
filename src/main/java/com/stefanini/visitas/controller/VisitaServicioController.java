package com.stefanini.visitas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefanini.visitas.dto.VisitaServicioDTO;
import com.stefanini.visitas.modelo.VisitaServicio;
import com.stefanini.visitas.service.VisitaServicioService;

@RestController
@CrossOrigin(origins = "https://demovisitas.azurewebsites.net")
@RequestMapping("/api/visita-servicio")
public class VisitaServicioController {
	
	@Autowired
    private VisitaServicioService visitaServicioService;


    @GetMapping("/{idVisita}")
    public List<VisitaServicio> obtenerServiciosPorVisita(@PathVariable Integer idVisita) {
        return visitaServicioService.obtenerServiciosPorVisita(idVisita);
    }
    
    @PostMapping("/{idVisita}/persona")
    public ResponseEntity<VisitaServicio> agregarPersona(
            @PathVariable Integer idVisita,
            @RequestBody @Valid VisitaServicioDTO visitaServicio) {

        VisitaServicio nuevaPersona = visitaServicioService.agregarPersona(idVisita, visitaServicio);
        return ResponseEntity.ok(nuevaPersona);
    }
    
    @PutMapping("/{idVisita}/persona/{idVisitaServicio}")
    public ResponseEntity<VisitaServicio> actualizarPersona(
            @PathVariable Integer idVisitaServicio,
            @PathVariable Integer idVisita,
            @RequestBody @Valid VisitaServicioDTO visitaServicio) {
        
        VisitaServicio personaActualizada = visitaServicioService.editarPersona(idVisitaServicio, idVisita, visitaServicio);
        return ResponseEntity.ok(personaActualizada);
    }
    
    @DeleteMapping("/{idVisita}/persona/{idVisitaServicio}")
    public void eliminarPersona(@PathVariable Integer idVisita, @PathVariable Integer idVisitaServicio) {
        visitaServicioService.eliminarPersona(idVisita, idVisitaServicio);
    }
}
