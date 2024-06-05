package es.tfgdm.repuesto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import es.tfgdm.coche.CocheDAO;
import es.tfgdm.suministra.SuministraDAO;
import jakarta.validation.Valid;

@Controller
public class RepuestoController {
	@Autowired
	RepuestoDAO repuestoDAO;

	@Autowired
	SuministraDAO suministraDAO;

	@Autowired
	CocheDAO cocheDAO;

	@GetMapping("/repuestos")
	public ModelAndView repuesto(@RequestParam(required = false) String keyword,
	                             @RequestParam(defaultValue = "1") int page,
	                             @RequestParam(defaultValue = "5") int size,
	                             @RequestParam(defaultValue = "id,asc") String[] sort) {

	    ModelAndView model = new ModelAndView();
	    model.setViewName("repuestos/repuestos");
	    try {
	        String sortField = sort[0];
	        String sortDirection = sort[1];
	        Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
	        Order order = new Order(direction, sortField);
	        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(order));
	        Page<Repuesto> listaRepuestos;
	        if (keyword == null || keyword.isEmpty()) {
	            listaRepuestos = repuestoDAO.findAll(pageable);
	        } else {
	            listaRepuestos = repuestoDAO.findByNombreContainingIgnoreCase(keyword, pageable);
	            model.addObject("keyword", keyword);
	        }
	        model.addObject("listaRepuestos", listaRepuestos);
	        model.addObject("currentPage", listaRepuestos.getNumber() + 1);
	        model.addObject("totalPages", listaRepuestos.getTotalPages());
	        model.addObject("totalItems", listaRepuestos.getTotalElements());
	    } catch (Exception e) {
	        model.addObject("message", e.getMessage());
	    }

	    return model;
	}


	@GetMapping("/repuestos/{id}")
	public ModelAndView getRepuestoPorId(@PathVariable String id) {
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
		if (repu.isPresent()) {
			model.addObject("repuesto", repu.get());
			model.addObject("coches", cocheDAO.findAll());
			model.setViewName("repuestos/repuestoForm");
		} else
			model.setViewName("redirect:/respuestos");
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
	public ModelAndView formRepuesto(@ModelAttribute @Valid Repuesto repuesto, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		if (bindingResult.hasErrors()) {
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
		ModelAndView model = new ModelAndView();
		if (repuesto.isPresent()) {
			Repuesto repuestoBuscado = repuesto.get();
			model.setViewName("redirect:/repuestos/" + repuestoBuscado.getId());
			return model;
		} else {
			model.setViewName("index");
			model.addObject("error", "Repuesto no encontrado para la referencia: " + id);

			return model;

		}
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
