package es.tfgdm.dao;


import java.util.Optional;

import es.tfgdm.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDAO extends CrudRepository<Usuario, Long>{
	
	boolean existsByUsername(String username);
	Optional<Usuario> findByUsername(String username);
}
