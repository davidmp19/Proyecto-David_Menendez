package com.spring.start.h2.repuesto;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface RepuestoDAO extends CrudRepository<Repuesto, Long> {

	 @Query("SELECT r FROM Repuesto r WHERE NOT EXISTS (SELECT su FROM Suministra su WHERE su.repuesto = r)")
	    List<Repuesto> findRepuestosNoVinculados();

	

	
}
