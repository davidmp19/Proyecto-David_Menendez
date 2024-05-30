package com.spring.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.coche.Coche;
import com.spring.proveedor.Proveedor;
import com.spring.repuesto.Repuesto;

@Controller
public class ControllerGeneral {
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
	    
	    
	    @GetMapping("/estadisticas")
	    public ModelAndView getEstadisticas() {
	        ModelAndView model = new ModelAndView();
	        model.setViewName("htmlsGenerales/estadisticas");

	        List<Repuesto> repuestosPesados = estadisticasService.getRepuestosByPesoMasGrandeQue(10);
	        List<Repuesto> repuestosPocasUni = estadisticasService.getRepuestosByUnidadesMenosQue(12);
	        List<Proveedor> proveedorDireccion= estadisticasService.getProveedoresByDireccionContaining("Oviedo");
	        List<Proveedor> proveedorRepuestos = 
	        		estadisticasService.getProveedoresByRepuestosCountGreaterThan(1);
	        List<Coche> getCochesByMarca= estadisticasService.getCochesByMarca("mercedes");
	        List<Coche> getCochesByRepuestoCount= estadisticasService.getCochesByRepuestoCountGreaterThan(1);

	        model.addObject("repuestosPesados", repuestosPesados);
	        model.addObject("repuestosPocasUni", repuestosPocasUni);
	        model.addObject("proveedorDireccion", proveedorDireccion);
	        model.addObject("proveedorRepuestos", proveedorRepuestos);
	        model.addObject("getCochesByMarca", getCochesByMarca);
	        model.addObject("getCochesByRepuestoCount", getCochesByRepuestoCount);
	        return model;
	    }
}
