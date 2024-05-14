package com.spring.start.h2.repuesto;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spring.start.h2.proveedor.Proveedor;



public interface RepuestoDAO extends CrudRepository<Repuesto, String> {

	 @Query("SELECT r FROM Repuesto r WHERE NOT EXISTS (SELECT su FROM Suministra su WHERE su.repuesto = r)")
	    List<Repuesto> findRepuestosNoVinculados();

	 Repuesto findByNombre(String nombre);
	
}
