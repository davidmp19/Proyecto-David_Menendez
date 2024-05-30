package com.spring.usuario;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioDao extends CrudRepository<Usuario, Long>{

	boolean existsByUsername(String username);
	Optional<Usuario> findByUsername(String username);
}
