package es.tfgdm.service;

import es.tfgdm.dao.CocheDAO;
import es.tfgdm.entity.Coche;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//Servicio para la gestión de entidades Coche
public class CocheService {

	@Autowired
	private CocheDAO cocheDAO;

	// Obtener todos los coches paginados
	public Page<Coche> findAll(Pageable pageable) {
		return cocheDAO.findAll(pageable);
	}

	// Obtener todos los coches
	public List<Coche> findAll() {
		return (List<Coche>) cocheDAO.findAll();
	}

	// Buscar coche por ID
	public Optional<Coche> findById(String id) {
		return cocheDAO.findById(id);
	}

	// Eliminar coche por ID
	public void deleteById(String id) {
		cocheDAO.deleteById(id);
	}

	// Verificar si existe un coche por su ID
	public boolean existsById(String id) {
		return cocheDAO.existsById(id);
	}

	// Guardar un coche
	public void save(Coche coche) {
		cocheDAO.save(coche);
	}

	// Buscar coches por marca
	public List<Coche> findByMarca(String marca) {
		return cocheDAO.findByMarca(marca);
	}

	// Buscar coches que tengan más de un cierto número de repuestos asociados
	public List<Coche> findCochesByRepuestoCountGreaterThan(int count) {
		return cocheDAO.findCochesByRepuestoCountGreaterThan(count);
	}
}
