package es.tfgdm.controller;

import java.util.List;
import java.util.Optional;

import es.tfgdm.entity.Suministra;
import es.tfgdm.service.ProveedorService;
import es.tfgdm.service.RepuestoService;
import es.tfgdm.service.SuministraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//Controlador de la relación suministra
@Controller
public class SuministraController {

	@Autowired
	SuministraService suministraService;

	@Autowired
	ProveedorService proveedorService;

	@Autowired
	RepuestoService repuestoService;

	// Método para obtener una lista con las relaciones entre repuestos y proveedores
	@GetMapping("/suministra")
	public ModelAndView getSuministra() {
		ModelAndView model = new ModelAndView();
		model.setViewName("suministra/suministra"); // Se establece la vista del listado de relaciones
		List<Suministra> listaSuministra = (List<Suministra>) suministraService.findAll(); // Se obtienen todos los registros de Suministra
		model.addObject("listaSuministra", listaSuministra); // Se añade la lista al modelo
		return model;
	}

	// Método para mostrar el formulario de creación de una nueva relación suministra
	@GetMapping("/suministra/add")
	public ModelAndView suministrar() {
		ModelAndView model = new ModelAndView();
		model.setViewName("suministra/suministraForm"); // Vista del formulario de suministro
		model.addObject("suministra", new Suministra()); // Se crea una nueva instancia de Suministra y se agrega al modelo
		model.addObject("repuestos", repuestoService.findAll()); // Se agregan todos los repuestos al modelo
		model.addObject("proveedores", proveedorService.findAll()); // Se agregan todos los proveedores al modelo
		return model;
	}

	// Método para eliminar una relación suministra por su ID
	@GetMapping("/suministra/del/{id}")
	public ModelAndView delSuministra(@PathVariable long id) {
		ModelAndView model = new ModelAndView();
		Optional<Suministra> suministraOptional = suministraService.findById(id);
		if (suministraOptional.isPresent()) {
			Suministra suministra = suministraOptional.get();
			// Desasociar el repuesto y el proveedor antes de eliminar la relación
			suministra.setProveedor(null);
			suministra.setRepuesto(null);
			suministraService.save(suministra);
			suministraService.deleteById(id); // Eliminar la relación suministra por su ID
		}
		model.setViewName("redirect:/suministra"); // Redirigir al listado de relaciones suministra
		return model;
	}

	// Método para guardar una nueva relación suministra
	@PostMapping("/suministra/save")
	public ModelAndView saveSuministra(@ModelAttribute Suministra suministra, RedirectAttributes redirectAttributes) {
		// Verificar si ya existe una relación entre el repuesto y el proveedor
		boolean asociacionExistente = suministraService.vinculados(suministra.getRepuesto().getNombre(),
				suministra.getProveedor().getNombre());

		if (!asociacionExistente) {
			suministraService.save(suministra); // Guardar la relación suministra
			return new ModelAndView("redirect:/suministra"); // Redirigir al listado de relaciones
		} else {
			// Mostrar un mensaje de error si la relación ya existe
			redirectAttributes.addFlashAttribute("error", "La relación ya existe");
			return new ModelAndView("redirect:/suministra/add"); // Redirigir al formulario de creación de relaciones
		}
	}
}