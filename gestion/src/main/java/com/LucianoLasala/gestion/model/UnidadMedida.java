package com.LucianoLasala.gestion.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UnidadMedida {
	private String tipo;
	
	@Id
	private Long idUnidadMedida;
	
	@OneToMany(mappedBy = "unidadMedida")
	private List<Insumo> listaInsumos = new ArrayList<>();

	public List<Insumo> getListaInsumos() {
		return listaInsumos;
	}

	public void setListaInsumos(List<Insumo> listaInsumos) {
		this.listaInsumos = listaInsumos;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getIdUnidadMedida() {
		return idUnidadMedida;
	}

	public void setIdUnidadMedida(Long idUnidadMedida) {
		this.idUnidadMedida = idUnidadMedida;
	}
	
	
}
