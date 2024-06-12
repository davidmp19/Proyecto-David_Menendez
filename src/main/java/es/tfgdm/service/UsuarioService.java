package es.tfgdm.service;

import es.tfgdm.dao.UsuarioDAO;
import es.tfgdm.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//Servicio para operaciones relacionadas con los usuarios
public class UsuarioService implements UserDetailsService {

	@Autowired
	UsuarioDAO usuarioDAO;

	// Método para cargar un usuario por su nombre de usuario
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioDAO.findByUsername(username);
		if (usuario.isPresent()) {
			return (UserDetails) usuario.get();
		}
		throw new UsernameNotFoundException(username);
	}

	// Obtener todos los usuarios
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDAO.findAll();
	}

	// Buscar usuario por su ID
	public Optional<Usuario> findById(Long id) {
		return usuarioDAO.findById(id);
	}

	// Eliminar usuario por su ID
	public void deleteById(Long id) {
		usuarioDAO.deleteById(id);
	}

	// Buscar usuario por su nombre de usuario
	public Optional<Usuario> findByUsername(String username) {
		return usuarioDAO.findByUsername(username);
	}

	// Guardar un usuario
	public void save(Usuario usuario) {
		usuarioDAO.save(usuario);
	}

	// Verificar si existe un usuario por su nombre de usuario
	public boolean existsByUsername(String newUsername) {
		return usuarioDAO.existsByUsername(newUsername);
	}
}