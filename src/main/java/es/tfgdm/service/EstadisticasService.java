package es.tfgdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.tfgdm.entity.Coche;
import es.tfgdm.entity.Proveedor;
import es.tfgdm.entity.Repuesto;

@Service
//Servicio para generar estadísticas relacionadas con repuestos, proveedores y coches
public class EstadisticasService {

	@Autowired
	private RepuestoService repuestoService;
	@Autowired
	private ProveedorService proveedorService;
	@Autowired
	private CocheService cocheService;

	// Obtener repuestos cuyo peso sea mayor que el valor dado
	public List<Repuesto> getRepuestosByPesoMasGrandeQue(int peso) {
		return repuestoService.findRepuestosByPesoMasGrandeQue(peso);
	}

	// Obtener repuestos cuyo número de unidades sea menor que el valor dado
	public List<Repuesto> getRepuestosByUnidadesMenosQue(int unidades) {
		return repuestoService.findRepuestosByUnidadesMenosQue(unidades);
	}

	// Obtener proveedores cuya dirección contenga la cadena dada
	public List<Proveedor> getProveedoresByDireccionContaining(String direccion) {
		return proveedorService.findProveedoresByDireccionContaining(direccion);
	}

	// Obtener proveedores que suministren más de cierto número de repuestos
	public List<Proveedor> getProveedoresByRepuestosCountGreaterThan(int count) {
		return proveedorService.findProveedoresByRepuestosCountGreaterThan(count);
	}

	// Obtener coches por marca
	public List<Coche> getCochesByMarca(String marca) {
		return cocheService.findByMarca(marca);
	}

	// Obtener coches con más de cierto número de repuestos asociados
	public List<Coche> getCochesByRepuestoCountGreaterThan(int count) {
		return cocheService.findCochesByRepuestoCountGreaterThan(count);
	}
}
