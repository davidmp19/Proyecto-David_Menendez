package es.tfgdm.proveedor;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.tfgdm.repuesto.Repuesto;
import es.tfgdm.suministra.Suministra;
import es.tfgdm.suministra.SuministraDAO;
import jakarta.validation.Valid;

@RestController
public class ProveedorController {
	
	
	
	@Autowired
	ProveedorDAO proveedorDAO;
	
	@Autowired
	SuministraDAO suministraDAO;
	
	@GetMapping("/proveedores")
	public ModelAndView proveedores() {

		ModelAndView model = new ModelAndView();
		model.setViewName("proveedores/proveedores");
		List<Proveedor> listaProveedores = (List<Proveedor>)proveedorDAO.findAll();
		
		model.addObject("listaProveedores", listaProveedores);
		
		return model;
	}
	
	@GetMapping("/proveedor/{dni}")
	public ModelAndView getProveedorPorDni(
			@PathVariable String dni) {
		Proveedor proveedor = proveedorDAO.findById(dni).get();
		
		ModelAndView model = new ModelAndView();
		model.setViewName("proveedores/proveedor");
		model.addObject("proveedor", proveedor);
		if (proveedor != null) {
			List<Suministra> suministros=suministraDAO.findByProveedorDni(proveedor.getDni());
			List<Repuesto> repuestos = suministros.stream()
					 								.map(Suministra::getRepuesto)
					 								.collect(Collectors.toList());
			model.addObject("repuestos", repuestos);
		}
		return model;
	}
	@GetMapping("/proveedor/edit/{dni}")
	public ModelAndView editPlan(@PathVariable String dni) {
				
		ModelAndView model = new ModelAndView();
		
		Optional<Proveedor> pro = proveedorDAO.findById(dni);
		if(pro.isPresent()) {
			
			model.addObject("proveedor", pro.get());
			model.addObject("updating", true);
			model.setViewName("proveedores/proveedorForm");
		}
		else model.setViewName("redirect:/proveedores");	
		
		return model;
	}	
	
	@GetMapping("/proveedor/add")
	public ModelAndView addProveedor() {
				

		ModelAndView model = new ModelAndView();
		model.setViewName("proveedores/proveedorForm");
		model.addObject("proveedor", new Proveedor());
		model.addObject("updating", false);
		return model;
	}	
	
	@GetMapping("/proveedor/del/{dni}")
	public ModelAndView delProveedor(@PathVariable String dni) {
				
		proveedorDAO.deleteById(dni);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/proveedores");
		
		return model;
	}	 
	
	@GetMapping("/proveedor/{dni}/repuesto")
	public Set<Repuesto> obtenerProveedoresDeRepuesto(@PathVariable String dni) {
	    List<Suministra> suministros = suministraDAO.findByProveedorDni(dni);
	    Set<Repuesto> repuestos = new HashSet<>();
	    
	    for (Suministra suministro : suministros) {
	        repuestos.add(suministro.getRepuesto());
	    }
	    
	    return repuestos;
	}

	@DeleteMapping("/proveedor/{dni}/repuesto/{id}")
	public ResponseEntity<String> eliminarRepuestoDeProveedor(
			@PathVariable String dni, 
			@PathVariable String id) {
		 Proveedor proveedor = proveedorDAO.findByDni(dni);
		    
		    if (proveedor != null) {
		        Optional<Suministra> suministro = suministraDAO.findByProveedorDniAndRepuestoId(dni, id);
		        
		        if (suministro.isPresent()) {
		            suministraDAO.delete(suministro.get());
		            return ResponseEntity.ok("Repuesto eliminado");
		        }
		    }
		    
		    return ResponseEntity.notFound().build();
		}
	@PostMapping("/proveedor/save")
	public ModelAndView proveedorForm(@ModelAttribute @Valid Proveedor proveedor,
			BindingResult bindingResult) {	
		ModelAndView model = new ModelAndView();
		if(bindingResult.hasErrors()) {
			model.setViewName("proveedores/proveedorForm");
			model.addObject("proveedor", new Proveedor());
			return model;
		}
	    proveedorDAO.save(proveedor);
		model.setViewName("redirect:/proveedores");	
		
		return model;
		
	}	 
	@GetMapping("proveedor/nuevo/{dni}")
	public ModelAndView nuevoProveedor(@PathVariable String dni) {

		ModelAndView model = new ModelAndView();

		List<Proveedor> proveedor = (List<Proveedor>) proveedorDAO.findAll();

		Proveedor proveedorNuevo = proveedorDAO.findById(dni).get();

	
			model.addObject("proveedor", new Proveedor());
			model.addObject("proveedor", proveedor);
			model.addObject("proveedorNuevo", proveedorNuevo);
			model.setViewName("proveedores/proveedor");
		
		

		

		return model;
	}
} 

