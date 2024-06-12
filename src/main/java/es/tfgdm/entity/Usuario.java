package es.tfgdm.entity;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="Usuario", uniqueConstraints= {@UniqueConstraint(columnNames= {"username"})})
public class Usuario implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String username;// Nombre de usuario

private String password;// Contraseña del usuario
 
@Transient
private String nuevoNombre; // Nombre temporal para la edición de nombre de usuario

//Getters y setters
public String getNuevoNombre() {
    return nuevoNombre;
}

public void setNuevoNombre(String nuevoNombre) {
    this.nuevoNombre = nuevoNombre;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}


public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}
//Asignar el rol de ADMIN a un usuario específico, de lo contrario, asignar USER
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	ArrayList<SimpleGrantedAuthority> permisos = new ArrayList<SimpleGrantedAuthority>();
	SimpleGrantedAuthority permiso;
	if(username.compareTo("david")==0) {
		permiso = new SimpleGrantedAuthority("ADMIN");
	}
	else{
		permiso = new SimpleGrantedAuthority("USER");
	}
	permisos.add(permiso); 
	
	return permisos;
}

@Override
public String getUsername() {
	// TODO Auto-generated method stub
	return username;
}

@Override
public boolean isAccountNonExpired() {
	// TODO Auto-generated method stub
	return true;
}

@Override
public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	return true;
}

@Override
public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return true;
}

@Override
public boolean isEnabled() {
	// TODO Auto-generated method stub
	return true;
}
 
 
}
