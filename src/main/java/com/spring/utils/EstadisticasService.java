package com.spring.utils;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.coche.Coche;
import com.spring.coche.CocheDAO;
import com.spring.proveedor.Proveedor;
import com.spring.proveedor.ProveedorDAO;
import com.spring.repuesto.Repuesto;
import com.spring.repuesto.RepuestoDAO;

@Service
public class EstadisticasService {
	 	@Autowired
	    private RepuestoDAO repuestoDao;
	    @Autowired
	    private ProveedorDAO proveedorDao;
	    @Autowired
	    private CocheDAO cocheDao;

	    public List<Repuesto> getRepuestosByPesoMasGrandeQue(int peso) {
	        return repuestoDao.findRepuestosByPesoMasGrandeQue(peso);
	    }
	    public List<Repuesto> getRepuestosByUnidadesMenosQue(int unidades) {
	        return repuestoDao.findRepuestosByUnidadesMenosQue(unidades);
	    } 
	    public List<Proveedor> getProveedoresByDireccionContaining(String direccion) {
	        return proveedorDao.findProveedoresByDireccionContaining(direccion);
	    }
	    public List<Proveedor> getProveedoresByRepuestosCountGreaterThan(int count) {
	        return proveedorDao.findProveedoresByRepuestosCountGreaterThan(count);
	    }
	    public List<Coche> getCochesByMarca(String marca) {
	        return cocheDao.findByMarca(marca);
	    }
	    public List<Coche> getCochesByRepuestoCountGreaterThan(int count) {
	        return cocheDao.findCochesByRepuestoCountGreaterThan(count);
	    }
}
