package com.LucianoLasala.gestion.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Insumo {
	private String nombre;
	private int iva, stock,equivalenciaEnUnidades; 
	private float precio,cantidadMinima;
	
	@Id
	private Long idInsumo;
	
	@ManyToOne()
	@JoinColumn(name = "id_categoria")
	private Categoria categoria = new Categoria();
	
	@ManyToOne()
	@JoinColumn(name = "id_unidadMedida")
	private UnidadMedida unidadMedida = new UnidadMedida();
	
	@ManyToOne()
	@JoinColumn(name = "id_unidadCompra")
	private UnidadCompra unidadCompra = new UnidadCompra();
	
	@OneToMany(mappedBy = "insumo")
	private List<FacturaInsumo> listaFacturaInsumo = new ArrayList<FacturaInsumo>();

	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public UnidadCompra getUnidadCompra() {
		return unidadCompra;
	}

	public void setUnidadCompra(UnidadCompra unidadCompra) {
		this.unidadCompra = unidadCompra;
	}

	public List<FacturaInsumo> getListaFacturaInsumo() {
		return listaFacturaInsumo;
	}

	public void setListaFacturaInsumo(List<FacturaInsumo> listaFacturaInsumo) {
		this.listaFacturaInsumo = listaFacturaInsumo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}


	public float getCantidadMinima() {
		return cantidadMinima;
	}

	public void setCantidadMinima(float cantidadMinima) {
		this.cantidadMinima = cantidadMinima;
	}

	public int getEquivalenciaEnUnidades() {
		return equivalenciaEnUnidades;
	}

	public void setEquivalenciaEnUnidades(int equivalenciaEnUnidades) {
		this.equivalenciaEnUnidades = equivalenciaEnUnidades;
	}

	public Long getIdInsumo() {
		return idInsumo;
	}

	public void setIdInsumo(Long idInsumo) {
		this.idInsumo = idInsumo;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	
	
}
