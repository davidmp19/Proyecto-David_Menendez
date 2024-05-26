package com.spring.start.h2.coche;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CocheDAO extends CrudRepository<Coche, String>{

	
	 	List<Coche> findByMarca(String marca);

	    @Query("SELECT c FROM Coche c WHERE SIZE(c.repuesto) > ?1")
	    List<Coche> findCochesByRepuestoCountGreaterThan(int count);
}
