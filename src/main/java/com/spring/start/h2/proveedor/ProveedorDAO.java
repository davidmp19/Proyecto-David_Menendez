package com.spring.start.h2.proveedor;

import java.util.List;
import org.springframework.data.repository.CrudRepository;


import com.spring.start.h2.repuesto.Repuesto;

public interface ProveedorDAO extends CrudRepository<Proveedor, String>{

	List<Proveedor> findByRepuestos(Repuesto repuesto);
	
	Proveedor findByDni(String dni);
}
