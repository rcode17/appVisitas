package com.stefanini.visitas.dto;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisitaDTO {
    private String user;
    private Integer codCliente;
    private Integer codProyecto;
    private Short idTipoVisita;
    private Date diaVisita;
    private Short idRango;
    private String modalidad;
    private String nombreVisitada;
    private String emailVisitada;
    private Boolean generaOportunidad;
    private Boolean esCliente;
    private String observaciones;
    private List<VisitaServicioDTO> servicios;
}
