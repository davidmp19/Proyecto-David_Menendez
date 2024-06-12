package es.tfgdm.service;

import es.tfgdm.dao.ProveedorDAO;
import es.tfgdm.entity.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//Servicio para operaciones relacionadas con proveedores
public class ProveedorService {

	@Autowired
	ProveedorDAO proveedorDAO;

	// Obtener todos los proveedores
	public List<Proveedor> findAll() {
		return (List<Proveedor>) proveedorDAO.findAll();
	}

	// Buscar proveedor por su DNI
	public Optional<Proveedor> findById(String dni) {
		return proveedorDAO.findById(dni);
	}

	// Eliminar proveedor por su DNI
	public void deleteById(String dni) {
		proveedorDAO.deleteById(dni);
	}

	// Buscar proveedor por su DNI
	public Proveedor findByDni(String dni) {
		return proveedorDAO.findByDni(dni);
	}

	// Verificar si existe un proveedor con el DNI dado
	public boolean existsById(String dni) {
		return proveedorDAO.existsById(dni);
	}

	// Guardar un proveedor
	public void save(Proveedor proveedor) {
		proveedorDAO.save(proveedor);
	}

	// Buscar proveedores cuya dirección contenga la cadena dada
	public List<Proveedor> findProveedoresByDireccionContaining(String direccion) {
		return proveedorDAO.findProveedoresByDireccionContaining(direccion);
	}

	// Buscar proveedores que suministren más de cierto número de repuestos
	public List<Proveedor> findProveedoresByRepuestosCountGreaterThan(int count) {
		return proveedorDAO.findProveedoresByRepuestosCountGreaterThan(count);
	}
}
