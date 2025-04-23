package com.stefanini.visitas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stefanini.visitas.modelo.Ingreso;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Integer> {
	public List<Ingreso> findByUserAndPwd(String usuario, String contrasena);
}
