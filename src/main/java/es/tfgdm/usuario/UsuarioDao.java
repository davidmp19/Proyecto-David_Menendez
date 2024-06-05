package es.tfgdm.usuario;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioDAO extends CrudRepository<Usuario, Long>{

	boolean existsByUsername(String username);
	Optional<Usuario> findByUsername(String username);
}
