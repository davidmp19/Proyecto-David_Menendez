package es.tfgdm.service;

import es.tfgdm.dao.RepuestoDAO;
import es.tfgdm.entity.Coche;
import es.tfgdm.entity.Repuesto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//Servicio para operaciones relacionadas con repuestos
public class RepuestoService {

	@Autowired
	RepuestoDAO repuestoDAO;

	// Buscar repuestos por coche
	public List<Repuesto> findByCoche(Coche coche) {
		return repuestoDAO.findByCoche(coche);
	}

	// Guardar un repuesto
	public void save(Repuesto repuesto) {
		repuestoDAO.save(repuesto);
	}

	// Obtener todos los repuestos paginados
	public Page<Repuesto> findAll(Pageable pageable) {
		return repuestoDAO.findAll(pageable);
	}

	// Obtener todos los repuestos
	public List<Repuesto> findAll() {
		return (List<Repuesto>) repuestoDAO.findAll();
	}

	// Buscar repuestos por nombre (ignorando mayúsculas/minúsculas)
	public Page<Repuesto> findByNombreContainingIgnoreCase(String keyword, Pageable pageable) {
		return repuestoDAO.findByNombreContainingIgnoreCase(keyword, pageable);
	}

	// Buscar repuesto por ID
	public Optional<Repuesto> findById(String id) {
		return repuestoDAO.findById(id);
	}

	// Eliminar repuesto por ID
	public void deleteById(String id) {
		repuestoDAO.deleteById(id);
	}

	// Verificar si existe un repuesto con el ID dado
	public boolean existsById(String id) {
		return repuestoDAO.existsById(id);
	}

	// Buscar repuestos cuyo peso sea mayor que el valor dado
	public List<Repuesto> findRepuestosByPesoMasGrandeQue(int peso) {
		return repuestoDAO.findRepuestosByPesoMasGrandeQue(peso);
	}

	// Buscar repuestos cuyo número de unidades sea menor que el valor dado
	public List<Repuesto> findRepuestosByUnidadesMenosQue(int unidades) {
		return repuestoDAO.findRepuestosByUnidadesMenosQue(unidades);
	}
}