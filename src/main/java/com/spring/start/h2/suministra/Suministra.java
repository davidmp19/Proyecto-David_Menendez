package com.spring.start.h2.suministra;


import com.spring.start.h2.proveedor.Proveedor;
import com.spring.start.h2.repuesto.Repuesto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Suministra {
	
	 @Id 
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idrepuesto") 
	private Repuesto repuesto; 
	@ManyToOne 
	@JoinColumn(name = "dniproveedor") 
	private Proveedor proveedor;
	
	
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
