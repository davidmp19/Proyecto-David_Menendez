package com.spring.start.h2.repuesto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import com.spring.start.h2.coche.Coche;
import com.spring.start.h2.proveedor.Proveedor;
import com.spring.start.h2.suministra.Suministra;




@Entity
public class Repuesto {

	
	@Id
	private String id;
	
	@Size(min=4, max=15, message="minimo 2 caracteres y maximo 30")
	private String nombre;
	
	@Min(value = 1, message = "El peso debe ser como mínimo 1")
	@Max(value = 2000, message = "El peso debe ser como máximo 2000")
	private int peso;	
	
	private String descripcion;
	
	@Min(value = 0, message = "Las unidades deben ser un número positivo")
	private int unidades;	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "FK_COCHE")
	private Coche coche;
	
	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	@OneToMany(
			targetEntity=Suministra.class,
			mappedBy="repuesto")
	private Set<Proveedor> proveedores = new HashSet<>();

	@OneToMany(
			targetEntity=Suministra.class,
			mappedBy="repuesto",
			cascade = CascadeType.ALL)
	private Set<Suministra> suministros = new HashSet<Suministra>();
	
	public Set<Suministra> getSuministros() {
		return suministros;
	}

	public void setSuministros(Set<Suministra> suministros) {
		this.suministros = suministros;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Set<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(Set<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	@Override
	public String toString() {
		return "Repuesto [id=" + id + ", nombre=" + nombre + ", peso=" + peso + ", descripcion=" + descripcion + ", unidades="
				+ unidades +"]";
	}

	







	

	
	

	
	

	


	
	

	
	
	
	


}
