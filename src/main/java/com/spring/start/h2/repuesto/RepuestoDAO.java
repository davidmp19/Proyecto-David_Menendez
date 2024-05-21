package com.spring.start.h2.repuesto;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.spring.start.h2.coche.Coche;


public interface RepuestoDAO extends CrudRepository<Repuesto, String> {

	 @Query("SELECT r FROM Repuesto r WHERE NOT EXISTS (SELECT su FROM Suministra su WHERE su.repuesto = r)")
	    List<Repuesto> findRepuestosNoVinculados();

	 List<Repuesto> findByCoche(Coche coche);
}
