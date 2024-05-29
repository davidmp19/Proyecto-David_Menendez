package com.spring.start.h2.usuario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.start.h2.utils.PasswordChangeDto;

import jakarta.validation.Valid;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioDao usuarioDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/usuarios")
    public ModelAndView usuarios() {
        ModelAndView model = new ModelAndView();
        model.setViewName("usuarios/usuarios");
        List<Usuario> listaUsuarios = (List<Usuario>) usuarioDao.findAll();
        model.addObject("listaUsuarios", listaUsuarios);
        return model;
    }

    @GetMapping("/usuarios/{id}")
    public ModelAndView getUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioDao.findById(id).orElse(null);
        ModelAndView model = new ModelAndView();
        model.setViewName("usuarios/usuario");
        model.addObject("usuario", usuario);
        return model;
    }

    @GetMapping("/usuario/registrar")
    public ModelAndView addUsuario() {
        ModelAndView model = new ModelAndView();
        model.setViewName("usuarios/registro");
        model.addObject("usuario", new Usuario());
        return model;
    }

    @GetMapping("/usuario/del/{id}")
    public ModelAndView delUsuario(@PathVariable Long id) {
        usuarioDao.deleteById(id);
        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/usuarios");
        return model;
    }

    @GetMapping("/usuario/edit/{id}")
    public ModelAndView editUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioDao.findById(id).orElse(null);
        ModelAndView model = new ModelAndView();
        model.setViewName("usuarios/usuarioForm");
        model.addObject("usuario", usuario);
        model.addObject("passwordChangeDto", new PasswordChangeDto());
        return model;
    }

    @PostMapping("/usuario/save")
    public ModelAndView formUsuario(@ModelAttribute @Valid Usuario usuario, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        if (bindingResult.hasErrors()) {
            model.setViewName("usuarios/registro");
            model.addObject("usuario", usuario);
            return model;
        }
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
        }
        usuarioDao.save(usuario);
        model.setViewName("redirect:/login");
        return model;  
    }
    @PostMapping("/usuario/updateUsername")
    public ModelAndView updateUsername(@ModelAttribute @Valid Usuario usuario, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        if (bindingResult.hasErrors()) {
            model.setViewName("usuarios/usuarioForm");
            model.addObject("usuario", usuario);
            return model;
        }
        Usuario existingUsuario = usuarioDao.findById(usuario.getId()).orElse(null);
        if (existingUsuario != null) {
            existingUsuario.setUsername(usuario.getNuevoNombre());
            usuarioDao.save(existingUsuario);
        } 
        model.setViewName("redirect:/usuarios");
        return model;
    }
	 
	@PostMapping("/usuario/updatePassword")
    public ModelAndView updatePassword(@ModelAttribute @Valid PasswordChangeDto passwordChangeDto, BindingResult bindingResult, @RequestParam Long id) {
        ModelAndView model = new ModelAndView();
        Usuario usuario = usuarioDao.findById(id).orElse(null);
 
        if (usuario == null) {
            bindingResult.rejectValue("currentPassword", "error.passwordChangeDto", "Usuario no encontrado");
            model.setViewName("usuarios/usuarioForm");
            model.addObject("usuario", new Usuario());
            return model;
        }

        if (bindingResult.hasErrors()) {
            model.setViewName("usuarios/usuarioForm");
            model.addObject("usuario", usuario);
            return model;
        }

        if (!bCryptPasswordEncoder.matches(passwordChangeDto.getCurrentPassword(), usuario.getPassword())) {
            bindingResult.rejectValue("currentPassword", "error.passwordChangeDto", "Contraseña actual incorrecta");
            model.setViewName("usuarios/usuarioForm");
            model.addObject("usuario", usuario);
            return model;
        }

        if (!passwordChangeDto.getNewPassword().equals(passwordChangeDto.getConfirmNewPassword())) {
            bindingResult.rejectValue("confirmNewPassword", "error.passwordChangeDto", "Las contraseñas no coinciden");
            model.setViewName("usuarios/usuarioForm");
            model.addObject("usuario", usuario);
            return model;
        }

        usuario.setPassword(bCryptPasswordEncoder.encode(passwordChangeDto.getNewPassword()));
        usuarioDao.save(usuario);
        model.setViewName("redirect:/usuarios");
        return model;
    }
}

