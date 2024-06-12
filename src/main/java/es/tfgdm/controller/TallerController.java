package es.tfgdm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TallerController {

	// Método para la vista de la pagina principal
	@GetMapping("/")
	public ModelAndView inicio() {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");// Se setea la vista principal
		return model;
	}

	// Método para la vista del inicio de sesion
	@GetMapping("/login")
	public ModelAndView getLogin() {
		ModelAndView model = new ModelAndView();
		model.setViewName("htmlsGenerales/login"); // Se setea la vista del login
		return model;
	}

	// Método para la vista del cerrar sesion
	@GetMapping("/logout")
	public ModelAndView getLogOut() {
		ModelAndView model = new ModelAndView();
		model.setViewName("htmlsGenerales/logout");// Se setea la vista del logout
		return model;
	}

	// Método para la vista de cuando un usuario no puede acceder a determinadas páginas
	@GetMapping("/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView model = new ModelAndView();
		model.setViewName("error/access-denied");// Se setea la vista del acceso denegado
		return model;
	}
}
