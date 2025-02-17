package com.stefanini.visitas.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "VisitaServicio")
@Getter
@Setter
public class VisitaServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdVisitaServicio")
    private Integer idVisitaServicio;

    @Column(name = "TipoPersona")
    private String tipoPersona;

    @Column(name = "NombrePersona")
    private String nombrePersona;

    @Column(name = "EmailPersona")
    private String emailPersona;

    @Column(name = "Observaciones")
    private String observaciones;

    @Column(name = "EsAlerta")
    private Boolean esAlerta;

    @ManyToOne
    @JoinColumn(name = "IdVisita", nullable = false)
    @JsonBackReference
    private Visita visita;
}

