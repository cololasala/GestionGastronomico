package com.LucianoLasala.gestion.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Proveedor {
	
	private String nombre;
	private int telefono;
	private int telefonoCelular;
	private String domicilio;
	private String ciudad;
	private String email;
	private String codigoPostal;
	private String observaciones;
	private boolean estadoProveedor;
	
	@Id
	private Long idProveedor;
	
	@OneToMany(mappedBy = "proveedor")
	private List<Factura> listaFacturas = new ArrayList<>();
	
	public List<Factura> getListaFacturas() {
		return listaFacturas;
	}
	public void setListaFacturas(List<Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}
	public Long getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public int getTelefonoCelular() {
		return telefonoCelular;
	}
	public void setTelefonoCelular(int telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public boolean isEstadoProveedor() {
		return estadoProveedor;
	}
	public void setEstadoProveedor(boolean estadoProveedor) {
		this.estadoProveedor = estadoProveedor;
	}
	
	
	
}
