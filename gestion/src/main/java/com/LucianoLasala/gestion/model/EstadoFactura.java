package com.LucianoLasala.gestion.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class EstadoFactura {
	private String estado;
	
	@Id
	private Long idEstadoFactura;
	
	@OneToMany(mappedBy = "estadoFactura")
	private List<Factura> ListaFacturas = new ArrayList<>();

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getIdEstadoFactura() {
		return idEstadoFactura;
	}

	public void setIdEstadoFactura(Long idEstadoFactura) {
		this.idEstadoFactura = idEstadoFactura;
	}
	
	
}
