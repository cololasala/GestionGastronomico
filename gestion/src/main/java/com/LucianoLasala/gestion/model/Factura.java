package com.LucianoLasala.gestion.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Factura {
	private String fecha;
	private String fechaPago;
	private int numeroFactura;
	private float totalFactura;
	private boolean eliminada;
	
	@Id
	@GeneratedValue
	private Long IdFactura;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_proveedor")
	private Proveedor proveedor = new Proveedor();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_estado_factura")
	private EstadoFactura estadoFactura = new EstadoFactura();
	
	@OneToMany(mappedBy= "factura", fetch = FetchType.EAGER)
	private List<FacturaInsumo> listaFacturaInsumo = new ArrayList<FacturaInsumo>();
	
	public int getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(int numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public EstadoFactura getEstadoFactura() {
		return estadoFactura;
	}

	public void setEstadoFactura(EstadoFactura estadoFactura) {
		this.estadoFactura = estadoFactura;
	}

	public List<FacturaInsumo> getListaFacturaInsumo() {
		return listaFacturaInsumo;
	}

	public void setListaFacturaInsumo(List<FacturaInsumo> listaFacturaInsumo) {
		this.listaFacturaInsumo = listaFacturaInsumo;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Long getIdFactura() {
		return IdFactura;
	}

	public void setIdFactura(Long idFactura) {
		IdFactura = idFactura;
	}

	public float getTotalFactura() {
		return totalFactura;
	}

	public void setTotalFactura(float totalFactura) {
		this.totalFactura = totalFactura;
	}

	public boolean isEliminada() {
		return eliminada;
	}

	public void setEliminada(boolean eliminada) {
		this.eliminada = eliminada;
	}
	
}
