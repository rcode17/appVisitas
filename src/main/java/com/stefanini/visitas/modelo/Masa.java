package com.stefanini.visitas.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Masa")
@Getter
@Setter
public class Masa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMasa")
    private Long idMasa;

    @Column(name = "PAIS")
    private String pais;

    @Column(name = "COD_PROYECTO")
    private String codProyecto;

    @Column(name = "DESC_PROYECTO")
    private String descProyecto;

    @Column(name = "DSM")
    private String dsm;

    @Column(name = "[USER]")
    private String user;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "DESC_PRACTICA")
    private String descPractica;

    @Column(name = "COD_CLIENTE")
    private String codCliente;

    @Column(name = "CLIENTE")
    private String cliente;
}
