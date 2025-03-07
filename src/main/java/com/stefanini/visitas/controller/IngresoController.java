package com.stefanini.visitas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefanini.visitas.dto.IngresoDTO;
import com.stefanini.visitas.modelo.Ingreso;
import com.stefanini.visitas.service.IngresoService;

@RestController
@RequestMapping("/api/ingreso")
public class IngresoController {
	
	@Autowired
    private IngresoService ingresoService;

    @GetMapping
    public List<Ingreso> getUserAll() {
        return ingresoService.obtenerTodosLosUsuarios();
    }
    
    @PostMapping("/login")
    public ResponseEntity<Ingreso> getUser(@RequestBody IngresoDTO dto) {
    	Ingreso user = ingresoService.getUser(dto.getUser(), dto.getPassword());
    	if(user == null) {
    		throw new IllegalArgumentException("Credenciales inválidas. intentalo de nuevo");
    	}
    	return ResponseEntity.ok(user);
    }
    
   
    
    
}
