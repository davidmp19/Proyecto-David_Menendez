package es.tfgdm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import es.tfgdm.coche.Coche;
import es.tfgdm.proveedor.Proveedor;
import es.tfgdm.repuesto.Repuesto;
import es.tfgdm.services.EstadisticasService;

@Controller
public class TallerController {
	@Autowired
	private EstadisticasService estadisticasService;

	@GetMapping("/")
	public ModelAndView inicio() {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}

	@GetMapping("/login")
	public ModelAndView getLogin() {
		ModelAndView model = new ModelAndView();
		model.setViewName("htmlsGenerales/login");
		return model;
	}

	@GetMapping("/logout")
	public ModelAndView getLogOut() {
		ModelAndView model = new ModelAndView();
		model.setViewName("htmlsGenerales/logout");
		return model;
	}

	@GetMapping("/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView model = new ModelAndView();
		model.setViewName("htmlsGenerales/access-denied");
		return model;
	}

	@GetMapping("/estadisticas")
	public ModelAndView getEstadisticas(
	    @RequestParam(name = "pesoMin", required = false, defaultValue = "10") int pesoMin,
	    @RequestParam(name = "unidadesMax", required = false, defaultValue = "12") Integer unidadesMax) {

	    ModelAndView model = new ModelAndView();
	    model.setViewName("htmlsGenerales/estadisticas");

	    List<Repuesto> repuestosPesados = estadisticasService.getRepuestosByPesoMasGrandeQue(pesoMin);
	    List<Repuesto> repuestosPocasUni = estadisticasService.getRepuestosByUnidadesMenosQue(unidadesMax);
	    List<Proveedor> proveedorDireccion = estadisticasService.getProveedoresByDireccionContaining("Oviedo");
	    List<Proveedor> proveedorRepuestos = estadisticasService.getProveedoresByRepuestosCountGreaterThan(1);
	    List<Coche> getCochesByMarca = estadisticasService.getCochesByMarca("mercedes");
	    List<Coche> getCochesByRepuestoCount = estadisticasService.getCochesByRepuestoCountGreaterThan(1);

	    model.addObject("repuestosPesados", repuestosPesados);
	    model.addObject("repuestosPocasUni", repuestosPocasUni);
	    model.addObject("proveedorDireccion", proveedorDireccion);
	    model.addObject("proveedorRepuestos", proveedorRepuestos);
	    model.addObject("getCochesByMarca", getCochesByMarca);
	    model.addObject("getCochesByRepuestoCount", getCochesByRepuestoCount);
	    model.addObject("pesoMin", pesoMin);
	    model.addObject("unidadesMax", unidadesMax);
	    return model;
	}
}
