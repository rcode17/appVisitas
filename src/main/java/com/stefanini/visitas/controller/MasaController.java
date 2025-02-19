package com.stefanini.visitas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefanini.visitas.modelo.Masa;
import com.stefanini.visitas.service.MasaService;

@RestController
@CrossOrigin(origins = "https://demovisitas.azurewebsites.net")
@RequestMapping("/api/masa")
public class MasaController {
	
	@Autowired
	private MasaService masaService;
	@GetMapping("/usuario/{user}")
    public List<Masa> obtenerPorUsuario(@PathVariable String user) {
        return masaService.obtenerPorUsuario(user);
    }
	
	@GetMapping()
    public List<Masa> obtenerTodosUsuario() {
        return masaService.obtenerTodosUsuario();
    }


}
