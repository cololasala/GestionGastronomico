package com.LucianoLasala.gestion.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categoria {
	private String nombre;
	
	@Id
	private Long idCategoria;
	
	@OneToMany(mappedBy = "categoria")
	private List<Insumo> listaInsumos = new ArrayList<>();

	public List<Insumo> getListaInsumos() {
		return listaInsumos;
	}

	public void setListaInsumos(List<Insumo> listaInsumos) {
		this.listaInsumos = listaInsumos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	
}
