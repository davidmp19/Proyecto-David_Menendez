package com.spring.start.h2.coche;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.spring.start.h2.repuesto.Repuesto;
import com.spring.start.h2.repuesto.RepuestoDAO;
import jakarta.validation.Valid;

@Controller
public class CocheController {
	
	@Autowired
	RepuestoDAO repuestoDAO;
	@Autowired
	CocheDAO cocheDAO;
	
	@GetMapping("/coches")
	public ModelAndView coches() {

		ModelAndView model = new ModelAndView();
		model.setViewName("coches/coches");
		List<Coche> listaCoches = (List<Coche>)cocheDAO.findAll();
		
		model.addObject("listaCoches", listaCoches);
		
		return model;
	}
	     
	@GetMapping("/coches/{id}")
	public ModelAndView getCochePorId(
			@PathVariable String id) {
		Coche coche = cocheDAO.findById(id).get();
		
		ModelAndView model = new ModelAndView();
		model.setViewName("coches/coche");
		model.addObject("coche", coche);
		
		return model; 
	} 
	@GetMapping("/coche/edit/{id}")
	public ModelAndView editCoche(@PathVariable String id) {
				    
		ModelAndView model = new ModelAndView();
		
		Optional<Coche> coch = cocheDAO.findById(id);
		if(coch.isPresent()) {
			
			model.addObject("coche", coch.get());
			model.setViewName("coches/cocheForm");
		}
		else model.setViewName("redirect:/coches");	
		
		return model;        
	}	
	
	@GetMapping("/coche/add")
	public ModelAndView addCoche() {
				
    
		ModelAndView model = new ModelAndView();
		model.setViewName("coches/cocheForm");
		model.addObject("coche", new Coche());
		return model;  
	}	
	
	@GetMapping("/coches/del/{id}")
	public ModelAndView delCoche(@PathVariable String id) {
	    ModelAndView model = new ModelAndView();
	    Optional<Coche> cocheOptional = cocheDAO.findById(id);
	    if (cocheOptional.isPresent()) {
	        Coche coche = cocheOptional.get();
	        List<Repuesto> repuestos = repuestoDAO.findByCoche(coche);
	        for (Repuesto repuesto : repuestos) {
	            repuesto.setCoche(null);
	            repuestoDAO.save(repuesto);
	        }
	        cocheDAO.deleteById(id);
	    }
	    model.setViewName("redirect:/coches");
	    return model;
		
		
	}	
	
		
	
	
	@PostMapping("/coche/save")
	public ModelAndView formCoche(@ModelAttribute @Valid Coche coche,
			BindingResult bindingResult) {	
		ModelAndView model = new ModelAndView();
		if(bindingResult.hasErrors()) {
			model.setViewName("coches/cocheForm");
			model.addObject("coche", coche);
			return model;
		}
	    cocheDAO.save(coche);
		model.setViewName("redirect:/coches");	
		
		return model;
		
	}	 
	 
	@GetMapping("coche/nuevo/{id}")
	public ModelAndView nuevoCoche(@PathVariable String id) {

		ModelAndView model = new ModelAndView();

		List<Coche> coches = (List<Coche>) cocheDAO.findAll();

		Coche cocheNuevo = cocheDAO.findById(id).get();
 
	
			model.addObject("coche", new Coche());
			model.addObject("coches", coches);
			model.addObject("cocheNuevo", cocheNuevo);
			model.setViewName("coches/coches");
		
		return model;
	}
}
