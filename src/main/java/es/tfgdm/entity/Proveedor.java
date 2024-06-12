package es.tfgdm.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Proveedor {

	@Id
	private String dni;

	private String nombre;

	private String direccion;

	@OneToMany(targetEntity = Suministra.class, mappedBy = "proveedor", cascade = CascadeType.ALL)
	private Set<Repuesto> repuestos = new HashSet<Repuesto>();

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
