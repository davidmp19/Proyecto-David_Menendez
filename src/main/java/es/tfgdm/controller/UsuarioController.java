package es.tfgdm.controller;

import java.util.List;
import java.util.Optional;

import es.tfgdm.entity.Usuario;
import es.tfgdm.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;
import es.tfgdm.dto.UsuarioChangeDTO;
import jakarta.validation.Valid;

//Controlador para gestionar las operaciones relacionadas con los usuarios
@RestController
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);


	// Método para mostrar la lista de usuarios
	@GetMapping("/usuarios")
	public ModelAndView usuarios() {
		log.info("Solicitando lista de usuarios");
		ModelAndView model = new ModelAndView();
		model.setViewName("usuarios/usuarios");
		List<Usuario> listaUsuarios = (List<Usuario>) usuarioService.findAll();
		model.addObject("listaUsuarios", listaUsuarios);
		log.debug("Número de usuarios encontrados: {}", listaUsuarios.size());
		return model;
	}

	// Método para ver los detalles de un usuario por su ID
	@GetMapping("/usuarios/{id}")
	public ModelAndView getUsuarioPorId(@PathVariable Long id) {
		log.info("Solicitando detalles del usuario con ID: {}", id);
		// Obtener el usuario autenticado actualmente
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuarioAutenticado = (Usuario) auth.getPrincipal();
		// Verificar si el usuario autenticado tiene permiso para ver el usuario solicitado
		if (!usuarioAutenticado.getId().equals(id)) {
			log.warn("El usuario autenticado no tiene permiso para ver el usuario solicitado");
			return new ModelAndView("redirect:/access-denied");
		}
		Usuario usuario = usuarioService.findById(id).orElse(null);
		ModelAndView model = new ModelAndView();
		model.setViewName("usuarios/usuario");
		model.addObject("usuario", usuario);
		return model;
	}

	// Método para mostrar el formulario de registro de un nuevo usuario
	@GetMapping("/usuario/registrar")
	public ModelAndView addUsuario() {
		log.info("Solicitando formulario de registro de nuevo usuario");
		ModelAndView model = new ModelAndView();
		model.setViewName("usuarios/registro");
		model.addObject("usuario", new Usuario());
		return model;
	}

	// Método para eliminar un usuario por su ID
	@GetMapping("/usuario/del/{id}")
	public ModelAndView delUsuario(@PathVariable Long id) {
		log.info("Eliminando usuario con ID: {}", id);
		usuarioService.deleteById(id);
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/usuarios");
		return model;
	}

	// Método para mostrar el formulario de edición de un usuario por su ID
	@GetMapping("/usuario/edit/{id}")
	public ModelAndView editUsuario(@PathVariable Long id) {
		log.info("Solicitando formulario de edición para el usuario con ID: {}", id);
		Usuario usuario = usuarioService.findById(id).orElse(null);
		ModelAndView model = new ModelAndView();
		model.setViewName("usuarios/usuarioForm");
		model.addObject("usuario", usuario);
		model.addObject("usuarioChangeDto", new UsuarioChangeDTO());
		return model;
	}

	// Método para procesar el registro de un nuevo usuario
	@PostMapping("/usuario/save")
	public ModelAndView formUsuario(@ModelAttribute @Valid Usuario usuario, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		// Verificar si ya existe un usuario con el mismo nombre de usuario
		Optional<Usuario> existingUser = usuarioService.findByUsername(usuario.getUsername());
		if (existingUser.isPresent()) {
			bindingResult.rejectValue("username", "error.usuario", "El nombre de usuario ya está en uso.");
		}
		// Verificar si hay errores de validación en el formulario
		if (bindingResult.hasErrors()) {
			model.setViewName("usuarios/registro");
			model.addObject("usuario", usuario);
			return model;
		}
		// Codificar la contraseña antes de guardarla en la base de datos
		if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
			usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		}
		// Guardar el usuario en la base de datos
		usuarioService.save(usuario);
		// Redireccionar al inicio de sesión después del registro exitoso
		model.setViewName("redirect:/login");
		return model;
	}

	// Método para actualizar el nombre de usuario
	@PostMapping("/usuario/updateUsername")
	public ModelAndView updateUsername(@ModelAttribute @Valid UsuarioChangeDTO usuarioChangeDto,
			BindingResult bindingResult, @RequestParam Long id) {
		ModelAndView model = new ModelAndView();
		Usuario usuario = usuarioService.findById(id).orElse(null);
		// Verificar si el usuario existe
		if (usuario == null) {
			bindingResult.rejectValue("newUsername", "error.usuarioChangeDto", "Usuario no encontrado");
			model.setViewName("usuarios/usuarioForm");
			model.addObject("usuario", new Usuario());
			return model;
		}
		// Verificar si hay errores de validación en el formulario
		if (bindingResult.hasErrors()) {
			model.setViewName("usuarios/usuarioForm");
			model.addObject("usuario", usuario);
			return model;
		}
		// Verificar si el nuevo nombre de usuario ya está en uso
		if (usuarioService.existsByUsername(usuarioChangeDto.getNewUsername())) {
			bindingResult.rejectValue("newUsername", "error.usuarioChangeDto",
					"El nuevo nombre de usuario ya está en uso");
			model.setViewName("usuarios/usuarioForm");
			model.addObject("usuario", usuario);
			return model;
		}
		// Actualizar el nombre de usuario y guardar los cambios
		usuario.setUsername(usuarioChangeDto.getNewUsername());
		usuarioService.save(usuario);
		// Redireccionar a la página de inicio
		model.setViewName("redirect:/");
		return model;
	}

	// Método para actualizar la contraseña del usuario
	@PostMapping("/usuario/updatePassword")
	public ModelAndView updatePassword(@ModelAttribute @Valid UsuarioChangeDTO usuarioChangeDto,
			BindingResult bindingResult, @RequestParam Long id) {
		ModelAndView model = new ModelAndView();
		Usuario usuario = usuarioService.findById(id).orElse(null);
		// Verificar si el usuario existe
		if (usuario == null) {
			bindingResult.rejectValue("currentPassword", "error.passwordChangeDto", "Usuario no encontrado");
			model.setViewName("usuarios/usuarioForm");
			model.addObject("usuario", new Usuario());
			return model;
		}
		// Verificar si hay errores de validación en el formulario
		if (bindingResult.hasErrors()) {
			model.setViewName("usuarios/usuarioForm");
			model.addObject("usuario", usuario);
			return model;
		}
		// Verificar si la contraseña actual es correcta
		if (!bCryptPasswordEncoder.matches(usuarioChangeDto.getCurrentPassword(), usuario.getPassword())) {
			bindingResult.rejectValue("currentPassword", "error.passwordChangeDto", "Contraseña actual incorrecta");
			model.setViewName("usuarios/usuarioForm");
			model.addObject("usuario", usuario);
			return model;
		}
		// Verificar si las contraseñas nuevas coinciden
		if (!usuarioChangeDto.getNewPassword().equals(usuarioChangeDto.getConfirmNewPassword())) {
			bindingResult.rejectValue("confirmNewPassword", "error.passwordChangeDto", "Las contraseñas no coinciden");
			model.setViewName("usuarios/usuarioForm");
			model.addObject("usuario", usuario);
			return model;
		}
		// Codificar la nueva contraseña y guardar los cambios
		usuario.setPassword(bCryptPasswordEncoder.encode(usuarioChangeDto.getNewPassword()));
		usuarioService.save(usuario);
		// Redireccionar a la página de inicio
		model.setViewName("redirect:/");
		return model;
	}
}
