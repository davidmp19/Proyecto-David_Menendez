package es.tfgdm.coche;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CocheDAO extends CrudRepository<Coche, String>{

	
	 	List<Coche> findByMarca(String marca);

	    @Query("SELECT c FROM Coche c WHERE SIZE(c.repuesto) > ?1")
	    List<Coche> findCochesByRepuestoCountGreaterThan(int count);
	    
		Page<Coche> findAll(Pageable pageable);
}
