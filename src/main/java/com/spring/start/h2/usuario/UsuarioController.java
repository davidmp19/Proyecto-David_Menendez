package com.spring.start.h2.usuario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import jakarta.validation.Valid;

@RestController
public class UsuarioController {
	
	@Autowired
	UsuarioDao usuarioDao;
	
	@GetMapping("/usuarios")
	public ModelAndView usuarios() {

		ModelAndView model = new ModelAndView();
		model.setViewName("usuarios/usuarios");
		List<Usuario> listaUsuarios = (List<Usuario>)usuarioDao.findAll();
		
		model.addObject("listaUsuarios", listaUsuarios);
		
		return model;
	}
	     
	@GetMapping("/usuarios/{user}")
	public ModelAndView getUsuarioPorId(
			@PathVariable String user) {
		Usuario usuario = usuarioDao.findById(user).get();
		
		ModelAndView model = new ModelAndView();
		model.setViewName("usuarios/usuario");
		model.addObject("usuario", usuario);
		
		return model; 
	} 
	
	@GetMapping("/usuario/add")
	public ModelAndView addUsuario() {
				
    
		ModelAndView model = new ModelAndView();
		model.setViewName("usuarios/usuarioForm");
		model.addObject("usuario", new Usuario());
		return model;  
	}	
	
	@GetMapping("/usuarios/del/{user}")
	public ModelAndView delUsuario(@PathVariable String user) {
	    ModelAndView model = new ModelAndView();
	    usuarioDao.deleteById(user);
	    
	    model.setViewName("redirect:/usuarios");
	    return model;
		 
		
	}	
	
		
	
	
	@PostMapping("/usuario/save")
	public ModelAndView formUsuario(@ModelAttribute @Valid Usuario usuario,
			BindingResult bindingResult) {	
		ModelAndView model = new ModelAndView();
		if(bindingResult.hasErrors()) {
			model.setViewName("usuarios/usuarioForm");
			model.addObject("usuario", usuario);
			return model;
		}
	    usuarioDao.save(usuario);
		model.setViewName("redirect:/usuarios");	
		
		return model;
		
	}	 
}
