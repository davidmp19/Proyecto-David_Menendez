package es.tfgdm.proveedor;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import es.tfgdm.repuesto.Repuesto;

public interface ProveedorDAO extends CrudRepository<Proveedor, String>{

	List<Proveedor> findByRepuestos(Repuesto repuesto);
	
	Proveedor findByDni(String dni);
	
	 @Query("SELECT p FROM Proveedor p WHERE p.direccion LIKE %?1%")
	 List<Proveedor> findProveedoresByDireccionContaining(String direccion);


	 @Query("SELECT p FROM Proveedor p WHERE SIZE(p.repuestos) > ?1")
	 List<Proveedor> findProveedoresByRepuestosCountGreaterThan(int count);
}