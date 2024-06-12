package es.tfgdm.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import es.tfgdm.entity.Proveedor;
import es.tfgdm.service.ProveedorService;
import es.tfgdm.service.SuministraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.tfgdm.entity.Repuesto;
import es.tfgdm.entity.Suministra;
import jakarta.validation.Valid;

//Controlador de proveedores
@Controller
public class ProveedorController {

	// Inyección de dependencias de los servicios necesarios
	@Autowired
	ProveedorService proveedorService;

	@Autowired
	SuministraService suministraService;

	// Método para mostrar una lista de proveedores
	@GetMapping("/proveedores")
	public ModelAndView proveedores() {

		ModelAndView model = new ModelAndView();
		model.setViewName("proveedores/proveedores");// Vista para el listado de proveedores
		List<Proveedor> listaProveedores = (List<Proveedor>) proveedorService.findAll();

		model.addObject("listaProveedores", listaProveedores); // Agregar lista de proveedores al modelo

		return model;
	}

	// Método para mostrar los detalles de un proveedor por su ID
	@GetMapping("/proveedor/{dni}")
	public ModelAndView getProveedorPorDni(@PathVariable String dni) {
		Proveedor proveedor = proveedorService.findById(dni).get();

		ModelAndView model = new ModelAndView();
		model.setViewName("proveedores/proveedor");// Vista para mostrar los detalles de un proveedor
		model.addObject("proveedor", proveedor); // Agregar proveedor al modelo
		if (proveedor != null) {
			// Obtener los repuestos suministrados por este proveedor
			List<Suministra> suministros = suministraService.findByProveedorDni(proveedor.getDni());
			List<Repuesto> repuestos = suministros.stream().map(Suministra::getRepuesto).collect(Collectors.toList());
			model.addObject("repuestos", repuestos);// Agregar repuestos suministrados al modelo
		}
		return model;
	}

	// Método para editar un proveedor existente
	@GetMapping("/proveedor/edit/{dni}")
	public ModelAndView editPlan(@PathVariable String dni) {

		ModelAndView model = new ModelAndView();

		Optional<Proveedor> pro = proveedorService.findById(dni);
		if (pro.isPresent()) {

			model.addObject("proveedor", pro.get());// Agregar proveedor al modelo
			model.addObject("updating", true);// Indicar que se está actualizando
			model.setViewName("proveedores/proveedorForm");// Vista para el formulario de edición de proveedor
		} else
			model.setViewName("redirect:/proveedores");// Redirigir al listado de proveedores si no se encuentra el
														// proveedor

		return model;
	}

	// Método para agregar un nuevo proveedor
	@GetMapping("/proveedor/add")
	public ModelAndView addProveedor() {

		ModelAndView model = new ModelAndView();
		model.setViewName("proveedores/proveedorForm");// Vista para el formulario de nuevo proveedor
		model.addObject("proveedor", new Proveedor());// Agregar nuevo proveedor al modelo
		model.addObject("updating", false);// Indicar que no se está actualizando si no creando
		return model;
	}

	// Método para eliminar un proveedor por su ID
	@GetMapping("/proveedor/del/{dni}")
	public ModelAndView delProveedor(@PathVariable String dni) {

		proveedorService.deleteById(dni);// Eliminar proveedor por su ID

		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/proveedores");// Redirigir al listado de proveedores

		return model;
	}

	// Método para guardar un proveedor (ya sea actualizando o creando uno nuevo)
	@PostMapping("/proveedor/save")
	public ModelAndView proveedorForm(@ModelAttribute @Valid Proveedor proveedor, BindingResult bindingResult,
			@RequestParam(required = false) boolean updating) {
		ModelAndView model = new ModelAndView();
		if (updating != true && bindingResult.hasErrors()) {
			model.setViewName("proveedores/proveedorForm");// Vista para el formulario de proveedor
			model.addObject("proveedor", proveedor);// Agregar proveedor con errores al modelo
			model.addObject("updating", updating); // Indicar que se está actualizando
			return model;
		}
		if (updating == false && proveedorService.existsById(proveedor.getDni())) {
			// Mostrar mensaje de error si el proveedor ya existe y se está creando uno
			// nuevo
			bindingResult.rejectValue("dni", "error.proveedor", "El dni ya pertenece a un proveedor");
			model.setViewName("proveedores/proveedorForm");// Vista para el formulario de proveedor
			model.addObject("proveedor", proveedor);// Agregar proveedor con error al modelo
			model.addObject("updating", updating);// Indicar que se está actualizando
			return model;
		}
		proveedorService.save(proveedor);// Guardar el proveedor
		model.setViewName("redirect:/proveedores");// Redirigir al listado de proveedores

		return model;

	}
}
