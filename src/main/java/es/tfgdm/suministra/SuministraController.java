package es.tfgdm.suministra;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.tfgdm.proveedor.ProveedorDAO;
import es.tfgdm.repuesto.RepuestoDAO;
 
@Controller
public class SuministraController {
	
	@Autowired
	SuministraDAO suministraDAO;
	
	@Autowired
	ProveedorDAO proveedorDAO;
	
	@Autowired
	RepuestoDAO repuestoDAO;
	
	@GetMapping("/suministra")
	public ModelAndView getSuministra() {

		ModelAndView model = new ModelAndView();
		model.setViewName("suministra/suministra");
		List<Suministra> listaSuministra = (List<Suministra>)suministraDAO.findAll();
		model.addObject("listaSuministra", listaSuministra);
		
		return model;
	}
	
	@GetMapping("/suministra/add")
	public ModelAndView suministrar() { 
				
		ModelAndView model = new ModelAndView();
		model.setViewName("suministra/suministraForm");
		model.addObject("suministra", new Suministra());
		    
		model.addObject("repuestos",  repuestoDAO.findAll());
		model.addObject("proveedores", proveedorDAO.findAll());
		
		
		 
		return model;
	}	
	
	@GetMapping("/suministra/del/{id}")
	public ModelAndView delSuministra(@PathVariable long id) {
				
		
		
		ModelAndView model = new ModelAndView();
		Optional<Suministra> suministraOptional = suministraDAO.findById(id);	
		if (suministraOptional.isPresent()) {
	        Suministra suministra = suministraOptional.get();
	        suministra.setProveedor(null);
	        suministra.setRepuesto(null);
	        suministraDAO.save(suministra);
	        suministraDAO.deleteById(id);
	    }
		model.setViewName("redirect:/suministra");
		return model; 
	}
		
 


	@PostMapping("/suministra/save")
	public ModelAndView saveSuministra(@ModelAttribute Suministra suministra, RedirectAttributes redirectAttributes) {

	    boolean asociacionExistente = suministraDAO.vinculados(
	        suministra.getRepuesto().getNombre(),
	        suministra.getProveedor().getNombre()
	    );

	    if (!asociacionExistente) {
	        suministraDAO.save(suministra);
	        return new ModelAndView("redirect:/suministra");
	    } else {
	        redirectAttributes.addFlashAttribute("error", "La relación ya existe");
	        return new ModelAndView("redirect:/suministra/add");
	    }
	}

}
