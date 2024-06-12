package es.tfgdm.controller;

import es.tfgdm.entity.Coche;
import es.tfgdm.entity.Proveedor;
import es.tfgdm.entity.Repuesto;
import es.tfgdm.service.EstadisticasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EstadisticasController {

	@Autowired
	private EstadisticasService estadisticasService;

	// Método para ver las estadisticas
	@GetMapping("/estadisticas")
	public ModelAndView getEstadisticas(// Dos request params para las dos estadisticas personalizables
			@RequestParam(name = "pesoMin", required = false, defaultValue = "10") int pesoMin,
			@RequestParam(name = "unidadesMax", required = false, defaultValue = "12") Integer unidadesMax) {

		ModelAndView model = new ModelAndView();
		model.setViewName("htmlsGenerales/estadisticas");// Se setea la vista de las estadisticas

		// Cada una de las estadisticas que se obtienen de un estadisticasService
		List<Repuesto> repuestosPesados = estadisticasService.getRepuestosByPesoMasGrandeQue(pesoMin);
		List<Repuesto> repuestosPocasUni = estadisticasService.getRepuestosByUnidadesMenosQue(unidadesMax);
		List<Proveedor> proveedorDireccion = estadisticasService.getProveedoresByDireccionContaining("Oviedo");
		List<Proveedor> proveedorRepuestos = estadisticasService.getProveedoresByRepuestosCountGreaterThan(1);
		List<Coche> getCochesByMarca = estadisticasService.getCochesByMarca("mercedes");
		List<Coche> getCochesByRepuestoCount = estadisticasService.getCochesByRepuestoCountGreaterThan(1);

		// Añade al model cada una de las estadisticas
		model.addObject("repuestosPesados", repuestosPesados);
		model.addObject("repuestosPocasUni", repuestosPocasUni);
		model.addObject("proveedorDireccion", proveedorDireccion);
		model.addObject("proveedorRepuestos", proveedorRepuestos);
		model.addObject("getCochesByMarca", getCochesByMarca);
		model.addObject("getCochesByRepuestoCount", getCochesByRepuestoCount);
		model.addObject("pesoMin", pesoMin);// Añade el valor de pesoMin al modelo
		model.addObject("unidadesMax", unidadesMax);// Añade el valor de unidadesMax al modelo
		return model;
	}

}
