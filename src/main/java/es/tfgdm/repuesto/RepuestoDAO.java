package es.tfgdm.repuesto;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import es.tfgdm.coche.Coche;


public interface RepuestoDAO extends CrudRepository<Repuesto, String> {

	 	@Query("SELECT r FROM Repuesto r WHERE NOT EXISTS (SELECT su FROM Suministra su WHERE su.repuesto = r)")
	    List<Repuesto> findRepuestosNoVinculados();

	 	List<Repuesto> findByCoche(Coche coche);
	 
	 	@Query("SELECT r FROM Repuesto r WHERE r.peso > ?1")
	    List<Repuesto> findRepuestosByPesoMasGrandeQue(int peso);

	    @Query("SELECT r FROM Repuesto r WHERE r.unidades < ?1")
	    List<Repuesto> findRepuestosByUnidadesMenosQue(int unidades);
}
