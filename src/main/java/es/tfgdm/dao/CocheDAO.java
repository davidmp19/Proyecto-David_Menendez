package es.tfgdm.dao;

import java.util.List;

import es.tfgdm.entity.Coche;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CocheDAO extends CrudRepository<Coche, String>{

		Page<Coche> findAll(Pageable pageable);
	 	
		//Dos de las querys de las estadisticas
	    @Query("SELECT c FROM Coche c WHERE SIZE(c.repuesto) > ?1")
	    List<Coche> findCochesByRepuestoCountGreaterThan(int count);
	    
	    List<Coche> findByMarca(String marca);
		
}
