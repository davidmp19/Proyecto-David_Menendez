package com.spring.suministra;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SuministraDAO extends CrudRepository<Suministra, Long>{
	
	 List<Suministra> findByRepuestoId(String id);
	 
	 List<Suministra> findByProveedorDni(String dni);
	 
	 Optional<Suministra> findByProveedorDniAndRepuestoId(String dni, String id);
	 
	 @Query("SELECT COUNT(su) > 0 FROM Suministra su WHERE su.repuesto.nombre = :nombreRepu AND su.proveedor.nombre = :nombreProv")
	    boolean vinculados(@Param("nombreRepu") String nombreRepu,@Param("nombreProv") String nombreProv);
	 
	
}
