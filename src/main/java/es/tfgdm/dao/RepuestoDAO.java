package es.tfgdm.dao;


import java.util.List;

import es.tfgdm.entity.Repuesto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import es.tfgdm.entity.Coche;


public interface RepuestoDAO extends CrudRepository<Repuesto, String> {

	 	@Query("SELECT r FROM Repuesto r WHERE NOT EXISTS (SELECT su FROM Suministra su WHERE su.repuesto = r)")
	    List<Repuesto> findRepuestosNoVinculados();

	 	List<Repuesto> findByCoche(Coche coche);
	 
	 	//Dos de las querys de las estadisticas
	 	@Query("SELECT r FROM Repuesto r WHERE r.peso > ?1")
	    List<Repuesto> findRepuestosByPesoMasGrandeQue(int peso);

	    @Query("SELECT r FROM Repuesto r WHERE r.unidades < ?1")
	    List<Repuesto> findRepuestosByUnidadesMenosQue(int unidades);

		Page<Repuesto> findAll(Pageable pageable);

		Page<Repuesto> findByNombreContainingIgnoreCase(String keyword, Pageable pageable);
}
