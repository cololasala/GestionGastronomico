package com.LucianoLasala.gestion.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class FacturaInsumo {
	private float precioParcialInsumo, cantidadInsumo;
	
	@Id
	@GeneratedValue
	private Long idFacturaInsumo;
	
	@ManyToOne
	@JoinColumn(name="idFactura")
	private Factura factura;
	
	@ManyToOne
	@JoinColumn(name="idInsumo")
	private Insumo insumo;

	public Long getIdFacturaInsumo() {
		return idFacturaInsumo;
	}

	public void setIdFacturaInsumo(Long idFacturaInsumo) {
		this.idFacturaInsumo = idFacturaInsumo;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	public float getCantidadInsumo() {
		return cantidadInsumo;
	}

	public void setCantidadInsumo(float cantidadInsumo) {
		this.cantidadInsumo = cantidadInsumo;
	}

	public float getPrecioParcialInsumo() {
		return precioParcialInsumo;
	}

	public void setPrecioParcialInsumo(float precioParcialInsumo) {
		this.precioParcialInsumo = precioParcialInsumo;
	}
	
	
}
