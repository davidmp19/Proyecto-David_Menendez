package es.tfgdm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Suministra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne // Indica una relación muchos a uno con Repuesto
	@JoinColumn(name = "idrepuesto")
	private Repuesto repuesto;
	@ManyToOne // Indica una relación muchos a uno con Proveedor
	@JoinColumn(name = "dniproveedor")
	private Proveedor proveedor;

	// Getter y setter
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Repuesto getRepuesto() {
		return repuesto;
	}

	public void setRepuesto(Repuesto repuesto) {
		this.repuesto = repuesto;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}
