package com.stefanini.visitas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisitaServicioDTO {
    private String tipoPersona;
    private String nombrePersona;
    private String emailPersona;
    private String observaciones;
    private Boolean esAlerta;
}