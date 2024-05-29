package com.spring.start.h2.usuario;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioDao extends CrudRepository<Usuario, Long>{


	Optional<Usuario> findByUsername(String username);
	
}
