package es.tfgdm.service;

import es.tfgdm.dao.SuministraDAO;
import es.tfgdm.entity.Suministra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//Servicio para operaciones relacionadas con la relación Suministra
public class SuministraService {

	@Autowired
	SuministraDAO suministraDAO;

	// Buscar suministros por ID de repuesto
	public List<Suministra> findByRepuestoId(String id) {
		return suministraDAO.findByRepuestoId(id);
	}

	// Buscar suministros por DNI de proveedor y ID de repuesto
	public Optional<Suministra> findByProveedorDniAndRepuestoId(String dni, String id) {
		return suministraDAO.findByProveedorDniAndRepuestoId(dni, id);
	}

	// Eliminar un suministro
	public void delete(Suministra suministra) {
		suministraDAO.delete(suministra);
	}

	// Buscar suministros por DNI de proveedor
	public List<Suministra> findByProveedorDni(String dni) {
		return suministraDAO.findByProveedorDni(dni);
	}

	// Obtener todos los suministros
	public List<Suministra> findAll() {
		return (List<Suministra>) suministraDAO.findAll();
	}

	// Buscar suministro por ID
	public Optional<Suministra> findById(long id) {
		return suministraDAO.findById(id);
	}

	// Guardar un suministro
	public void save(Suministra suministra) {
		suministraDAO.save(suministra);
	}

	// Eliminar suministro por ID
	public void deleteById(long id) {
		suministraDAO.deleteById(id);
	}

	// Verificar si existe una vinculación entre un repuesto y un proveedor
	public boolean vinculados(String nombre, String nombre1) {
		return suministraDAO.vinculados(nombre, nombre1);
	}
}
