package com.stefanini.visitas.modelo;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Visita")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visita {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdVisita")
    private Integer idVisita;

    @Column(name = "[USER]")
    private String user;

    @Column(name = "COD_CLIENTE")
    private String codCliente;

    @Column(name = "COD_PROYECTO")
    private String codProyecto;

    @ManyToOne
    @JoinColumn(name = "IdTipoVisita", nullable = false)
    private TipoVisita tipoVisita;

    @Column(name = "DiaVisita")
    private Date diaVisita;

    @ManyToOne
    @JoinColumn(name = "IdRango", nullable = false)
    private RangoVisita rangoVisita;

    @Column(name = "Modalidad")
    private String modalidad;

    @Column(name = "NombreVisitada")
    private String nombreVisitada;

    @Column(name = "EmailVisitada")
    private String emailVisitada;

    @Column(name = "GeneraOportunidad")
    private Boolean generaOportunidad;

    @Column(name = "EsCliente")
    private Boolean esCliente;

    @Column(name = "Observaciones")
    private String observaciones;
    
    @OneToMany(mappedBy = "visita", cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
    @JsonManagedReference
    private List<VisitaServicio> servicios;
}
