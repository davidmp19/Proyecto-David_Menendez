package es.tfgdm.controller;

import java.util.List;

import java.util.Optional;

import es.tfgdm.entity.Coche;
import es.tfgdm.service.CocheService;
import es.tfgdm.service.RepuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.tfgdm.entity.Repuesto;
import jakarta.validation.Valid;

//Controlador de coches
@Controller
public class CocheController {
	// Inyección de dependencias de los servicios necesarios
	@Autowired
	RepuestoService repuestoService;
	@Autowired
	CocheService cocheService;

	// Método para obtener una lista de coches que esta paginada
	@GetMapping("/coches")
	public ModelAndView coches(@RequestParam(defaultValue = "0") int page) {
		int size = 5;// Numero de elementos por página
		Pageable pageable = PageRequest.of(page, size);
		ModelAndView model = new ModelAndView();
		model.setViewName("coches/coches");// Se setea la vista del listado de coche
		Page<Coche> listaCoches = cocheService.findAll(pageable);
		model.addObject("listaCoches", listaCoches);// Añadir lista de coches al modelo
		model.addObject("totalPages", listaCoches.getTotalPages()); // Añadir total de páginas para que se vean en el
																	// pie de la tabla
		return model;
	}

	// Método para obtener los detalles de un coche por su ID
	@GetMapping("/coches/{id}")
	public ModelAndView getCochePorId(@PathVariable String id) {
		Coche coche = cocheService.findById(id).get();// Obtener coche por ID
		ModelAndView model = new ModelAndView();
		model.setViewName("coches/coche");// Se setea la vista del coche
		model.addObject("coche", coche);// Añadir coche al modelo
		return model;
	}

	// Método para editar un coche existente
	@GetMapping("/coche/edit/{id}")
	public ModelAndView editCoche(@PathVariable String id) {
		ModelAndView model = new ModelAndView();
		Optional<Coche> coch = cocheService.findById(id);
		if (coch.isPresent()) {
			model.addObject("coche", coch.get());// Añadir coche al modelo
			model.addObject("updating", true);// Indicar que se está actualizando por el uso del mismo html para añadir
												// y editar
			model.setViewName("coches/cocheForm");// Se setea la vista del Formulario de coche
		} else
			model.setViewName("redirect:/coches");// Redirigir a la tabla si no se encuentra el coche

		return model;
	}

	// Método para agregar un nuevo coche
	@GetMapping("/coche/add")
	public ModelAndView addCoche() {
		ModelAndView model = new ModelAndView();
		model.setViewName("coches/cocheForm"); // Vista para el formulario de coche
		model.addObject("coche", new Coche());// Añadir un nuevo coche vacío al modelo
		model.addObject("updating", false);// Indicar que no se está actualizando
		return model;
	}

	// Método para eliminar un coche por ID
	@GetMapping("/coches/del/{id}")
	public ModelAndView delCoche(@PathVariable String id) {
		ModelAndView model = new ModelAndView();
		Optional<Coche> cocheOptional = cocheService.findById(id);
		if (cocheOptional.isPresent()) {
			Coche coche = cocheOptional.get();
			List<Repuesto> repuestos = repuestoService.findByCoche(coche);
			// Desasociar repuestos del coche antes de eliminarlo
			for (Repuesto repuesto : repuestos) {
				repuesto.setCoche(null);
				repuestoService.save(repuesto);
			}
			cocheService.deleteById(id);// Eliminar coche por ID
		}
		model.setViewName("redirect:/coches");// Redirigir a la tabla de coches
		return model;
	}

	// Método para guardar un coche ya sea actualizando o creando
	@PostMapping("/coche/save")
	public ModelAndView formCoche(@ModelAttribute @Valid Coche coche, BindingResult bindingResult,
			@RequestParam(required = false) boolean updating) {
		ModelAndView model = new ModelAndView();
		// Validación de errores para un coche nuevo
		if (!updating && bindingResult.hasErrors()) {
			model.setViewName("coches/cocheForm");// Vista para el formulario de coche
			model.addObject("coche", coche); // Añade el coche con errores al modelo para mostrar los errores en el
												// formulario
			model.addObject("updating", updating);
			return model;
		}
		// Validación para evitar ID duplicados en coches nuevos
		if (!updating && cocheService.existsById(coche.getId())) {
			bindingResult.rejectValue("id", "error.coche", "El ID ya existe"); // Añade un error específico para el
																				// campo ID
			model.setViewName("coches/cocheForm");// Vista para el formulario de coche
			model.addObject("coche", coche);// Añade el coche con el error al modelo para mostrar los errores en el
											// formulario
			model.addObject("updating", updating);
			return model;
		}

		cocheService.save(coche);// Guardar coche en la base de datos
		model.setViewName("redirect:/coches");// Redirigir a la lista de coches con los nuevos o actualizados
		return model;
	}

}
