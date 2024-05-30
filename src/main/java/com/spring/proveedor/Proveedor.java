package com.spring.proveedor;
import java.util.HashSet;
import java.util.Set;

import com.spring.repuesto.Repuesto;
import com.spring.suministra.Suministra;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity
public class Proveedor {

	@Id
	private String dni;
	
	@Size(min=4, max=30, message="minimo 4 caracteres y maximo 15")
	private String nombre;
	
	@Size(min=5, max=50, message="La dirección debe tener menos de 50 caracteres")
	private String direccion;
	

	@OneToMany(
			targetEntity=Suministra.class,
			mappedBy="proveedor",
			cascade = CascadeType.ALL)
	private Set<Repuesto> repuestos = new HashSet<Repuesto>();

	@OneToMany(
			targetEntity=Suministra.class,
			mappedBy="proveedor")
	private Set<Suministra> suministros = new HashSet<>();

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Set<Repuesto> getRepuestos() {
		return repuestos;
	}
	public void setRepuestos(Set<Repuesto> repuestos) {
		this.repuestos = repuestos;
	}
	@Override
	public String toString() {
		return "Proveedor [dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + ", repuestos=" + repuestos
				+ "]";
	}
	



	
	
}
