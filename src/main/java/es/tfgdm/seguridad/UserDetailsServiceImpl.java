package es.tfgdm.seguridad;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.tfgdm.usuario.Usuario;
import es.tfgdm.usuario.UsuarioDao;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired 
	private UsuarioDao usuarioDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioDao.findByUsername(username);
		if(usuario.isPresent()) {
			return (UserDetails)usuario.get();
		}
		throw new UsernameNotFoundException(username);
	}
}
