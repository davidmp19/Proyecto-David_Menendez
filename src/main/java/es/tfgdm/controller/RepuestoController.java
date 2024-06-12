package es.tfgdm.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import es.tfgdm.entity.Repuesto;
import es.tfgdm.service.CocheService;
import es.tfgdm.service.RepuestoService;
import es.tfgdm.service.SuministraService;
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

import es.tfgdm.entity.Proveedor;
import es.tfgdm.entity.Suministra;
import jakarta.validation.Valid;

//Controlador para la gestión de repuestos
@Controller
public class RepuestoController {
	// Inyección de dependencias de los servicios necesarios
	@Autowired
	RepuestoService repuestoService;

	@Autowired
	SuministraService suministraService;

	@Autowired
	CocheService cocheService;

	// Método para mostrar la lista de repuestos
	@GetMapping("/repuestos")
	public ModelAndView repuesto(@RequestParam(required = false) String keyword,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "id,asc") String[] sort) {

		ModelAndView model = new ModelAndView();
		model.setViewName("repuestos/repuestos");
		try {
			// Extracción del campo de ordenación y dirección del parámetro de orden
			String sortField = sort[0];
			String sortDirection = sort[1];
			Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
			Order order = new Order(direction, sortField);
			// Creación de objeto Pageable para la paginación y ordenación
			Pageable pageable = PageRequest.of(page - 1, size, Sort.by(order));
			Page<Repuesto> listaRepuestos;
			// Si no se proporciona una palabra clave, se obtienen todos los repuestos
			if (keyword == null || keyword.isEmpty()) {
				listaRepuestos = repuestoService.findAll(pageable);
			} else {
				// Si se proporciona una palabra clave, se buscan los repuestos que contienen esa palabra en su nombre
				listaRepuestos = repuestoService.findByNombreContainingIgnoreCase(keyword, pageable);
				model.addObject("keyword", keyword);
			}
			// Agregar objetos al modelo para su visualización en la vista
			model.addObject("listaRepuestos", listaRepuestos);
			model.addObject("currentPage", listaRepuestos.getNumber() + 1);
			model.addObject("totalPages", listaRepuestos.getTotalPages());
			model.addObject("totalItems", listaRepuestos.getTotalElements());
		} catch (Exception e) {
			// En caso de error, se agrega un mensaje de error al modelo
			model.addObject("message", e.getMessage());
		}

		return model;
	}

	// Método para mostrar los detalles de un repuesto por su ID
	@GetMapping("/repuestos/{id}")
	public ModelAndView getRepuestoPorId(@PathVariable String id) {
		// Obtener el repuesto por su ID
		Repuesto repuesto = repuestoService.findById(id).get();
		ModelAndView model = new ModelAndView();
		model.setViewName("repuestos/repuesto");
		model.addObject("repuesto", repuesto);
		if (repuesto != null) {
			// Si el repuesto existe, se obtienen los proveedores que suministran este repuesto
			List<Suministra> suministros = suministraService.findByRepuestoId(repuesto.getId());
			// Se extraen los proveedores de los suministros
			List<Proveedor> proveedores = suministros.stream().map(Suministra::getProveedor)
					.collect(Collectors.toList());
			// Se agregan los proveedores al modelo para su visualización en la vista
			model.addObject("proveedores", proveedores);
		}
		return model;
	}

	// Método para editar un repuesto existente
	@GetMapping("/repuesto/edit/{id}")
	public ModelAndView editRepuesto(@PathVariable String id) {
		ModelAndView model = new ModelAndView();
		Optional<Repuesto> repu = repuestoService.findById(id);
		if (repu.isPresent()) {
			// Si el repuesto existe, se agrega al modelo junto con la lista de coches y se abre el formulario de edición
			model.addObject("repuesto", repu.get());
			model.addObject("updating", true);
			model.addObject("coches", cocheService.findAll());
			model.setViewName("repuestos/repuestoForm");
		} else
			// Si el repuesto no existe, se redirige a la lista de repuestos
			model.setViewName("redirect:/respuestos");
		return model;
	}

	// Método para agregar un nuevo repuesto
	@GetMapping("/repuesto/add")
	public ModelAndView addRepuesto() {
		ModelAndView model = new ModelAndView();
		// Se abre el formulario de repuesto para agregar uno nuevo
		model.setViewName("repuestos/repuestoForm");
		model.addObject("updating", false);
		model.addObject("repuesto", new Repuesto());
		model.addObject("coches", cocheService.findAll());
		return model;
	}

	// Método para eliminar un repuesto por su ID
	@GetMapping("/repuestos/del/{id}")
	public ModelAndView delRepuesto(@PathVariable String id) {
		// Eliminar el repuesto por su ID
		repuestoService.deleteById(id);
		ModelAndView model = new ModelAndView();
		// Redirigir a la lista de repuestos
		model.setViewName("redirect:/repuestos");
		return model;
	}

	// Método para guardar un repuesto, ya sea actualizando o creando uno nuevo
	@PostMapping("/repuesto/save")
	public ModelAndView formRepuesto(@ModelAttribute @Valid Repuesto repuesto, BindingResult bindingResult,
			@RequestParam(required = false) boolean updating) {
		ModelAndView model = new ModelAndView();
		// Validación de errores
		if (updating != true && bindingResult.hasErrors()) {
			// Si no se está actualizando y hay errores, se recarga el formulario de repuesto con los errores mostrados
			model.setViewName("repuestos/repuestoForm");
			model.addObject("repuesto", repuesto);
			model.addObject("updating", updating);
			model.addObject("coches", cocheService.findAll());
			return model;
		}
		// Validación para evitar ID duplicados
		if (updating == false && repuestoService.existsById(repuesto.getId())) {
			// Si no se está actualizando y el ID ya existe, se muestra un mensaje de error
			bindingResult.rejectValue("id", "error.repuesto", "El ID ya existe");
			model.setViewName("repuestos/repuestoForm");
			model.addObject("repuesto", repuesto);
			model.addObject("updating", updating);
			model.addObject("coches", cocheService.findAll());
			return model;
		}
		// Guardar el repuesto
		repuestoService.save(repuesto);
		// Redirigir a la lista de repuestos
		model.setViewName("redirect:/repuestos");
		return model;
	}

	// Método para buscar un repuesto por su ID
	@GetMapping("/buscarRepuesto")
	public ModelAndView buscarRepuesto(@RequestParam("id") String id) {
		// Buscar el repuesto por su ID
		Optional<Repuesto> repuesto = repuestoService.findById(id);
		ModelAndView model = new ModelAndView();
		if (repuesto.isPresent()) {
			// Si el repuesto existe, redirigir a la página de detalles de ese repuesto
			Repuesto repuestoBuscado = repuesto.get();
			model.setViewName("redirect:/repuestos/" + repuestoBuscado.getId());
			return model;
		} else {
			// Si el repuesto no existe, mostrar un mensaje de error y redirigir a la página
			// de inicio
			model.setViewName("index");
			model.addObject("error", "Repuesto no encontrado para la referencia: " + id);
			return model;
		}
	}
}
