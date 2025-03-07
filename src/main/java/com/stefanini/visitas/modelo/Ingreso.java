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
@Table(name = "Ingreso")
@Getter
@Setter
public class Ingreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUsuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "PAIS")
    private String pais;

    @Column(name = "[USER]")
    private String user;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "PWD")
    private String pwd;
}
