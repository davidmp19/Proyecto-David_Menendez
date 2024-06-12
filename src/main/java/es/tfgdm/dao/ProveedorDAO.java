package es.tfgdm.dao;

import java.util.List;

import es.tfgdm.entity.Proveedor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import es.tfgdm.entity.Repuesto;

public interface ProveedorDAO extends CrudRepository<Proveedor, String>{

	List<Proveedor> findByRepuestos(Repuesto repuesto);
	
	Proveedor findByDni(String dni);
	
	//Dos de las querys de las estadisticas
	 @Query("SELECT p FROM Proveedor p WHERE p.direccion LIKE %?1%")
	 List<Proveedor> findProveedoresByDireccionContaining(String direccion);


	 @Query("SELECT p FROM Proveedor p WHERE SIZE(p.repuestos) > ?1")
	 List<Proveedor> findProveedoresByRepuestosCountGreaterThan(int count);
}
