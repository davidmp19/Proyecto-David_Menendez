package es.tfgdm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Repuesto {

	
	@Id
	private String id;
	
	private String nombre;
	
	private int peso;	
	
	private String descripcion;
	
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
			mappedBy="repuesto",
			cascade = CascadeType.ALL)
	private Set<Proveedor> proveedores = new HashSet<Proveedor>();


	public Set<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(Set<Proveedor> proveedores) {
		this.proveedores = proveedores;
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
