package es.tfgdm.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "coche")
public class Coche {

	@Id
	private String id;

	private String marca;
	private String modelo;

	// Aqui se relaciona el coche con los repuestos donde los coches pueden tener
	// muchos repuestos
	@OneToMany(mappedBy = "coche", fetch = FetchType.EAGER)
	private List<Repuesto> repuesto = new ArrayList<Repuesto>();

	// Getters y setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public List<Repuesto> getRepuesto() {
		return repuesto;
	}

	public void setRepuesto(List<Repuesto> repuesto) {
		this.repuesto = repuesto;
	}

	@Override
	public String toString() {
		return "Coche [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", repuesto=" + repuesto + "]";
	}

}
