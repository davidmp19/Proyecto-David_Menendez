package com.spring.start.h2.repuesto;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.start.h2.coche.CocheDAO;
import com.spring.start.h2.suministra.SuministraDAO;

import jakarta.validation.Valid;



@Controller    
public class RepuestoController {
	@Autowired
	RepuestoDAO repuestoDAO;
	
	@Autowired
	SuministraDAO suministraDAO;
	
	@Autowired
	CocheDAO cocheDAO; 
	
	@GetMapping("/")
	public ModelAndView inicio() {

		ModelAndView model = new ModelAndView();
		model.setViewName("index");

		return model;
	} 
	@GetMapping("/repuestos")
	public ModelAndView repuesto() {

		ModelAndView model = new ModelAndView();
		model.setViewName("repuestos/repuestos");
		List<Repuesto> listaRepuestos = (List<Repuesto>)repuestoDAO.findAll();
		
		model.addObject("listaRepuestos", listaRepuestos);
		
		return model;
	}
	
	@GetMapping("/repuestos/{id}")
	public ModelAndView getRepuestoPorId(
			@PathVariable String id) {
		Repuesto repuesto = repuestoDAO.findById(id).get();
		
		ModelAndView model = new ModelAndView();
		model.setViewName("repuestos/repuesto");
		model.addObject("repuesto", repuesto);
		
		return model; 
	} 
	@GetMapping("/repuesto/edit/{id}")
	public ModelAndView editRepuesto(@PathVariable String id) {
				
		ModelAndView model = new ModelAndView();
		
		Optional<Repuesto> repu = repuestoDAO.findById(id);
		if(repu.isPresent()) {
			
			model.addObject("repuesto", repu.get());
			model.addObject("coches", cocheDAO.findAll());
			model.setViewName("repuestos/repuestoForm");
		}
		else model.setViewName("redirect:/respuestos");	
		
		return model;
	}	
	
	@GetMapping("/repuesto/add")
	public ModelAndView addRepuesto() {
				

		ModelAndView model = new ModelAndView();
		model.setViewName("repuestos/repuestoForm");
		model.addObject("repuesto", new Repuesto());
		model.addObject("coches", cocheDAO.findAll());
		return model;  
	}	
	
	@GetMapping("/repuestos/del/{id}")
	public ModelAndView delRepuesto(@PathVariable String id) {
		repuestoDAO.deleteById(id);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/repuestos");
		
		return model;
	}	
	
	@PostMapping("/repuesto/save")
	public ModelAndView formRepuesto(@ModelAttribute @Valid Repuesto repuesto,
			BindingResult bindingResult) {	
		ModelAndView model = new ModelAndView();
		if(bindingResult.hasErrors()) {
			model.setViewName("repuestos/repuestoForm");
			model.addObject("repuesto", repuesto);
			model.addObject("coches", cocheDAO.findAll());	
			return model;
		}
	    repuestoDAO.save(repuesto);
		model.setViewName("redirect:/repuestos");	
		
		return model;
		
	}	 
	@GetMapping("/buscarRepuesto")
	public ModelAndView buscarRepuesto(@RequestParam("id") String id) {
	    Optional<Repuesto> repuesto = repuestoDAO.findById(id);
	    	Repuesto repuestoBuscado =repuesto.get();
	        ModelAndView model = new ModelAndView();
	        model.setViewName("redirect:/repuestos/"+repuestoBuscado.getId());
	        return model;
	        } 
	 
	@GetMapping("repuesto/nuevo/{id}")
	public ModelAndView nuevoRepuesto(@PathVariable String id) {

		ModelAndView model = new ModelAndView();

		List<Repuesto> repuestos = (List<Repuesto>) repuestoDAO.findAll();

		Repuesto repuestoNuevo = repuestoDAO.findById(id).get();
 
	
			model.addObject("repuesto", new Repuesto());
			model.addObject("coches", cocheDAO.findAll());
			model.addObject("repuestos", repuestos);
			model.addObject("repuestoNuevo", repuestoNuevo);
			model.setViewName("repuestos/repuestos");
		
		return model;
	}
}
	
	
	
	
	

