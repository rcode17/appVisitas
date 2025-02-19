package com.stefanini.visitas.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stefanini.visitas.dto.VisitaDTO;
import com.stefanini.visitas.modelo.Visita;
import com.stefanini.visitas.service.VisitaService;

@RestController
@CrossOrigin(origins = "https://demovisitas.azurewebsites.net")
@RequestMapping("/api/visitas")
public class VisitaController {
	
	@Autowired
    private VisitaService visitaService;
	

    @GetMapping
    public List<Visita> obtenerTodasLasVisitas() {
        return visitaService.obtenerTodasLasVisitas();
    }
    
    @GetMapping("/v2")
    public ResponseEntity<?> obtenerVisitas(
        @RequestParam(required = false) Integer idVisita, 
        @RequestParam(required = false) String user) {

            if (idVisita != null) {
            	return ResponseEntity.ok(List.of(visitaService.obtenerPorId(idVisita)));
            } 

            if (user != null) {
                return ResponseEntity.ok(visitaService.findByUser(user));
            }

            return ResponseEntity.ok(visitaService.obtenerTodasLasVisitas());
            
    }
    
    @GetMapping("/usuario/{user}")
    public List<Visita> obtenerVisitaPorUsuario(@PathVariable String user) {
        return visitaService.findByUser(user);
    }

    @GetMapping("/{id}")
    public Visita obtenerVisitaPorId(@PathVariable Integer id) {
        return visitaService.obtenerPorId(id);
    }
    
    @PostMapping
    public ResponseEntity<Visita> guardarVisita(@RequestBody VisitaDTO visitaDTO) {
        Visita nuevaVisita = visitaService.guardarVisita(visitaDTO);
        return ResponseEntity.ok(nuevaVisita);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Visita> editarVisita(@PathVariable Integer id, @RequestBody VisitaDTO visita) {
        Visita visitaEditada = visitaService.editarVisita(id, visita);
        return ResponseEntity.ok(visitaEditada);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarVisita(@PathVariable Integer id) {
        visitaService.eliminarVisita(id);
        return ResponseEntity.ok("Visita eliminada correctamente.");
    }


}
