package com.LucianoLasala.gestion.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;

import java.util.Iterator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.LucianoLasala.gestion.model.Factura;
import com.LucianoLasala.gestion.model.FacturaInsumo;
import com.LucianoLasala.gestion.model.Insumo;
import com.LucianoLasala.gestion.model.Proveedor;
import com.LucianoLasala.gestion.repository.ICategoria;
import com.LucianoLasala.gestion.repository.IEstadoFactura;
import com.LucianoLasala.gestion.repository.IFactura;
import com.LucianoLasala.gestion.repository.IFacturaInsumo;
import com.LucianoLasala.gestion.repository.IInsumo;
import com.LucianoLasala.gestion.repository.IProveedor;
import com.LucianoLasala.gestion.repository.IUnidadMedida;


@Controller
public class GestionController {
	@Autowired
	private IProveedor proveedorRepo;
	@Autowired
	private IFactura facturaRepo;
	@Autowired
	private IInsumo insumoRepo;
	@Autowired
	private ICategoria categoriaRepo;
	@Autowired
	private IUnidadMedida unidadMedidaRepo;
	@Autowired
	private IFacturaInsumo facturaInsumo;
	@Autowired
	private IEstadoFactura estadoFacturaRepo;
	
	List<Insumo> listaInsumosViejos = new ArrayList<Insumo>();
	List<Insumo> listaInsumos = new ArrayList<Insumo>();					// se declara una lista para insumos
	float total;															// el total es de la factura al sumar cada insumo nuevo
	long idFactura = 0;														// idFactura necesario cuando se requiere modifcar una factura
	Date myDate = new Date();												// variable para obtener la fecha
	List<FacturaInsumo> listaFacturaInsumo = new ArrayList<FacturaInsumo>();	
	List<Integer> listaNroFacturas = new ArrayList<Integer>();
	List<Integer> listaNroFacturasP1 = new ArrayList<Integer>();
	List<Integer> listaNroFacturasP2 = new ArrayList<Integer>();
	List<Integer> listaNroFacturasP3 = new ArrayList<Integer>();

	@RequestMapping(value = "/inicio", method = RequestMethod.GET)			//inicio get , es la pagina principal de la app
	public String inicio(Model model) {
		return "inicio";
	}
	
	@RequestMapping(value="/factura", method = RequestMethod.GET)		//factura get, es el formulario para crear una nueva factura
	public String factura(Model model) {
		listaInsumos.clear();										//lista que se vacia cuando se entra a /factura por error luego de ingresar en /guardarIsumo
		this.listaNroFacturas.clear();
		this.total = 0.00f;
		listaNroFacturasP1.clear();
		listaNroFacturasP2.clear();
		listaNroFacturasP3.clear();
		for (Factura f: facturaRepo.findAll()) {
			if(f.getProveedor().getIdProveedor() == 1L) {
				this.listaNroFacturasP1.add(f.getNumeroFactura());
				System.out.println(f.getNumeroFactura());
			} else if(f.getProveedor().getIdProveedor() == 2L) {
				this.listaNroFacturasP2.add(f.getNumeroFactura());
			} else {
				this.listaNroFacturasP3.add(f.getNumeroFactura());
			}
		}
		model.addAttribute("spinner", true);
		model.addAttribute("factura", new Factura());
		model.addAttribute("insumo", new Insumo());
		model.addAttribute("listaNroFacturasP1",this.listaNroFacturasP1);
		model.addAttribute("listaNroFacturasP2",this.listaNroFacturasP2);
		model.addAttribute("listaNroFacturasP3",this.listaNroFacturasP3);
		model.addAttribute("proveedores", proveedorRepo.findAll());
		model.addAttribute("categorias", categoriaRepo.findAll());
		model.addAttribute("insumos", insumoRepo.findAll());
		model.addAttribute("medida",unidadMedidaRepo.findAll());
		model.addAttribute("estados",estadoFacturaRepo.findAll());
		model.addAttribute("listaInsumos",listaInsumos);
		model.addAttribute("total",total);
		return "factura";
	}
	
	@RequestMapping(value="/agregarInsumo", method=RequestMethod.POST)							//agregarInsumo post, agrega insumo de una factura nueva
	public String agregarInsumo(@ModelAttribute Insumo insumo, Model model) {
		if(insumo.getUnidadMedida().getTipo() != insumoRepo.findById(insumo.getIdInsumo()).get().getUnidadMedida().getTipo()) {
			model.addAttribute("showWarning",true);
		} else {
		this.total = 0.00F;											// el total se va generando cada vez que agrego un insumo
		insumo.setNombre(insumoRepo.findById(insumo.getIdInsumo()).get().getNombre());

		Iterator<Insumo> iterator = this.listaInsumos.iterator();	// si se agrega un insumo que ya esta en la lista, se reemplaza 
		while(iterator.hasNext()) {
			Insumo i = iterator.next();
			
			if(i.getIdInsumo() == insumo.getIdInsumo()) {
				iterator.remove();
			}
		};
			this.listaInsumos.add(insumo);
	
			for(Insumo x: listaInsumos) {
				
				 this.total = this.total + (x.getPrecio() * x.getCantidadMinima()); 
			};
		};
		model.addAttribute("spinner", false);
		model.addAttribute("listaNroFacturasP1",this.listaNroFacturasP1);
		model.addAttribute("listaNroFacturasP2",this.listaNroFacturasP2);
		model.addAttribute("listaNroFacturasP3",this.listaNroFacturasP3);
		model.addAttribute("listaInsumos",listaInsumos);
		model.addAttribute("factura",new Factura());
		model.addAttribute("insumo", new Insumo());
		model.addAttribute("proveedores", proveedorRepo.findAll());
		model.addAttribute("categorias", categoriaRepo.findAll());
		model.addAttribute("insumos", insumoRepo.findAll());
		model.addAttribute("medida",unidadMedidaRepo.findAll());
		model.addAttribute("estados",estadoFacturaRepo.findAll());
		model.addAttribute("total",this.total);
		return "factura";
	}
	
	@RequestMapping(value = "/eliminarInsumo/{idInsumo}", method = RequestMethod.GET)		//eliminarInsumo get, elimina insumo de una factura nueva
	public String eliminarInsumo(@PathVariable(name= "idInsumo") int id, Model model) {
		Iterator<Insumo> iterator = this.listaInsumos.iterator();
		while(iterator.hasNext()) {															//eliminacion de insumo de la listaInsumo
			Insumo insumo = iterator.next();
			if(insumo.getIdInsumo() == id) {
				iterator.remove();
			};
		};
		this.total = 0.00F;
		for(Insumo x: this.listaInsumos) {
	 		this.total = this.total + (x.getPrecio() * x.getCantidadMinima());
	 	};
	 	model.addAttribute("spinner", false);
		model.addAttribute("factura",new Factura());
		model.addAttribute("insumo", new Insumo());
		model.addAttribute("myAlertDeleteProductSuccess",true);							//agregado para delete producto
		model.addAttribute("listaNroFacturasP1",this.listaNroFacturasP1);
		model.addAttribute("listaNroFacturasP2",this.listaNroFacturasP2);
		model.addAttribute("listaNroFacturasP3",this.listaNroFacturasP3);
		model.addAttribute("proveedores", proveedorRepo.findAll());
		model.addAttribute("categorias", categoriaRepo.findAll());
		model.addAttribute("insumos", insumoRepo.findAll());
		model.addAttribute("medida",unidadMedidaRepo.findAll());
		model.addAttribute("estados",estadoFacturaRepo.findAll());
		model.addAttribute("listaInsumos",this.listaInsumos);
		model.addAttribute("total",this.total);
		return "factura";
	}
	
	@RequestMapping(value="/guardarFactura", method=RequestMethod.POST)						//guardarFactura post, guarda una nueva factura
	public String guardarFactura(@ModelAttribute Factura factura, Model model) {
		if(factura.getEstadoFactura().getIdEstadoFactura() == 1L) {
			factura.setFechaPago(new SimpleDateFormat("yyyy-MM-dd").format(myDate));
		}
		factura.setTotalFactura(this.total);
		factura.setEliminada(false); 													// factura eliminada false
		facturaRepo.save(factura);
		for(Insumo x : this.listaInsumos) {
			FacturaInsumo fi = new FacturaInsumo ();
			fi.setCantidadInsumo(x.getCantidadMinima());
			fi.setFactura(factura);
			fi.setInsumo(x);
			fi.setPrecioParcialInsumo(x.getPrecio()); 					// precio parcial
			facturaInsumo.save(fi);
		}
		float deuda = 0;
		float pagado = 0;
		for(Factura f: proveedorRepo.findById(facturaRepo.findById(factura.getIdFactura()).get().getProveedor().getIdProveedor()).get().getListaFacturas()) {
			if(f.isEliminada() == false) {														//verificacion eliminada
				if(f.getEstadoFactura().getIdEstadoFactura() == 2F) {
					deuda = deuda + f.getTotalFactura();
				} else {
					pagado = pagado + f.getTotalFactura();
				}
			}
		}
		model.addAttribute("showAlert",true);
		model.addAttribute("pagado",pagado);
		model.addAttribute("deuda",deuda);
		model.addAttribute("proveedor",proveedorRepo.findById(facturaRepo.findById(factura.getIdFactura()).get().getProveedor().getIdProveedor()).get());
		model.addAttribute("facturas",proveedorRepo.findById(facturaRepo.findById(factura.getIdFactura()).get().getProveedor().getIdProveedor()).get().getListaFacturas());
		return "detalle";											//cambiovista
	}
	
	@RequestMapping(value="/eliminarFactura/{idProveedor}/factura/{id}", method=RequestMethod.GET)				//eliminarFactura get, elimina una factura en estado No pago
	public String eliminarFactura(@PathVariable("idProveedor") long idProveedor, @PathVariable("id") long idFactura, Model model) {
		System.out.println(facturaRepo.findById(idFactura).get().getEstadoFactura().getIdEstadoFactura());
		if(facturaRepo.findById(idFactura).get().getEstadoFactura().getIdEstadoFactura() == 1L) {
			model.addAttribute("showWarning",true);
		} else {
			Factura f = new Factura();
			f = facturaRepo.findById(idFactura).get();
			f.setEliminada(true);
			facturaRepo.save(f);
	//		facturaRepo.delete(facturaRepo.findById(idFactura).get()); 
			model.addAttribute("showSuccess",true); // permite mostrar el alert de factura eliminada correctamente
		}
		float deuda = 0;
		float pagado = 0;
		for(Factura f: proveedorRepo.findById(idProveedor).get().getListaFacturas()) {
			if(f.isEliminada() == false) {
				if(f.getEstadoFactura().getIdEstadoFactura() == 2F) {
					deuda = deuda + f.getTotalFactura();
				} else {
					pagado = pagado + f.getTotalFactura();
				}
			}
		}
		model.addAttribute("pagado",pagado);
		model.addAttribute("deuda",deuda);
		model.addAttribute("proveedor",proveedorRepo.findById(idProveedor).get());
		model.addAttribute("facturas",proveedorRepo.findById(idProveedor).get().getListaFacturas());
		return "detalle";
	}
	
	@RequestMapping(value="/detalle/{id}", method=RequestMethod.GET)					//detalle get, es el listado de factura de un proveedor
	public String detalle(@PathVariable("id") long idProveedor, Model model) {
		float deuda = 0;
		float pagado = 0;
		model.addAttribute("facturas",proveedorRepo.findById(idProveedor).get().getListaFacturas()); /* recupero la lista de facturas de ese proveedor*/
		model.addAttribute("proveedor",proveedorRepo.findById(idProveedor).get());
		for(Factura f: proveedorRepo.findById(idProveedor).get().getListaFacturas()) {
			if(f.isEliminada() == false) {
				if(f.getEstadoFactura().getIdEstadoFactura() == 2F) {
					deuda = deuda + f.getTotalFactura();
				} else {
					pagado = pagado + f.getTotalFactura();
				}
			}	
		}
		model.addAttribute("pagado",pagado);
		model.addAttribute("deuda",deuda);
		return "detalle";
	}
	
	@RequestMapping(value="/modificarFactura/{idProveedor}/factura/{id}",method=RequestMethod.GET)				//modificarFactura get, es el formulario para modificar una factura ya creada
	public String modificarFactura(@PathVariable("idProveedor") long idProveedor, @PathVariable("id") long idFactura, Model model) {
		this.listaInsumosViejos.clear(); 		//insumos viejos
		this.idFactura = idFactura;
		this.listaInsumos.clear();				//verificar si es necesario aca...
		this.total = 0;							//verificar si es necesario aca...
		facturaRepo.findById(idFactura);		
		proveedorRepo.findById(idProveedor);
		
		for(FacturaInsumo i: facturaRepo.findById(idFactura).get().getListaFacturaInsumo()) {
			Insumo insumo = new Insumo();
			insumo = i.getInsumo();
			insumo.setPrecio(i.getPrecioParcialInsumo());				//al insumo se le setea el precio parcial(que esta en la tabla intermedia)
			insumo.setCantidadMinima(i.getCantidadInsumo());			//lo mismo pero en cantidad
			this.listaInsumos.add(insumo);
			this.listaInsumosViejos.add(insumo);
		};
		for(Factura f:proveedorRepo.findById(idProveedor).get().getListaFacturas()) { // lista nroFacturas del proveedor
			if(f.getIdFactura() != idFactura) {
				this.listaNroFacturas.add(f.getNumeroFactura());
			}
		}
		model.addAttribute("spinner", true);
		model.addAttribute("listaNroFacturas",this.listaNroFacturas);
		model.addAttribute("factura",facturaRepo.findById(idFactura).get());
		model.addAttribute("proveedor",proveedorRepo.findById(idProveedor).get());
		model.addAttribute("listaInsumos",this.listaInsumos);
		model.addAttribute("total",facturaRepo.findById(idFactura).get().getTotalFactura());
		model.addAttribute("estado",facturaRepo.findById(idFactura).get().getEstadoFactura().getIdEstadoFactura());
		model.addAttribute("insumo",new Insumo());
		model.addAttribute("insumos",insumoRepo.findAll());
		model.addAttribute("medida",unidadMedidaRepo.findAll());
		return "modificar_factura";
	}
	
	@RequestMapping(value="/agregarInsumoModificar", method=RequestMethod.POST)						//agregarInsumoModificar post, cuando se agrega un nuevo insumo a una factura modificada
	public String agregarInsumoModificar(@ModelAttribute Insumo insumo,@ModelAttribute Factura factura, Model model) {
		if(insumo.getUnidadMedida() != insumoRepo.findById(insumo.getIdInsumo()).get().getUnidadMedida()) {
			model.addAttribute("showWarning",true);
		} else {
		this.total = 0.00F;											// el total se va generando cada vez que agrego un insumo
		insumo.setNombre(insumoRepo.findById(insumo.getIdInsumo()).get().getNombre());
		Iterator<Insumo> iterator = this.listaInsumos.iterator();	// si se agrega un insumo que ya esta en la lista, se reemplaza 
		while(iterator.hasNext()) {
			Insumo i = iterator.next();
			if(i.getIdInsumo() == insumo.getIdInsumo()) {
				iterator.remove();
			}
		}
		this.listaInsumos.add(insumo);

		for(Insumo x: listaInsumos) {
			this.total = this.total + (x.getPrecio() * x.getCantidadMinima());
		};
		};
		model.addAttribute("spinner", false);
		model.addAttribute("listaInsumos",listaInsumos);
		model.addAttribute("factura",facturaRepo.findById(this.idFactura).get());
		model.addAttribute("insumo", new Insumo());
		model.addAttribute("insumos", insumoRepo.findAll());
		model.addAttribute("medida",unidadMedidaRepo.findAll());
		model.addAttribute("estados",estadoFacturaRepo.findAll());
		model.addAttribute("total",this.total);
		model.addAttribute("estado",facturaRepo.findById(this.idFactura).get().getEstadoFactura().getIdEstadoFactura());
		model.addAttribute("listaNroFacturas",this.listaNroFacturas);
		return "modificar_factura";
		
	}
	@RequestMapping(value = "/modificarEliminarInsumo/{idInsumo}", method = RequestMethod.GET)		//modificarEliminarInsumo get, es la eliminacion de un insumo de una factura modificada
	public String modificarEliminarInsumo(@PathVariable(name= "idInsumo") int id, Model model) {
		Iterator<Insumo> iterator = this.listaInsumos.iterator();
		while(iterator.hasNext()) {															//eliminacion de insumo de la listaInsumo
			Insumo insumo = iterator.next();
			if(insumo.getIdInsumo() == id) {
				iterator.remove();
			}
		}
		this.total = 0.00F;
		for(Insumo x: this.listaInsumos) {
	 		this.total = this.total + (x.getPrecio() * x.getCantidadMinima());
	 	};
	 	model.addAttribute("spinner", false);
	 	model.addAttribute("myAlertDeleteProductSuccess",true);							//agregado para delete producto
	 	model.addAttribute("listaNroFacturas",this.listaNroFacturas);
		model.addAttribute("factura",facturaRepo.findById(this.idFactura).get());
		model.addAttribute("insumo", new Insumo());
		model.addAttribute("insumos", insumoRepo.findAll());
		model.addAttribute("medida",unidadMedidaRepo.findAll());
		model.addAttribute("estados",estadoFacturaRepo.findAll());
		model.addAttribute("listaInsumos",this.listaInsumos);
		model.addAttribute("estado",facturaRepo.findById(this.idFactura).get().getEstadoFactura().getIdEstadoFactura());
		model.addAttribute("total",this.total);
		return "modificar_factura";
	}
	
	@RequestMapping(value="/modificarFactura",method=RequestMethod.POST)							//modificarFactura post, es el guardado de una factura que fue modificada
	public String modificarFactura(@ModelAttribute Factura factura, @ModelAttribute Insumo insumo, Model model) {
		model.addAttribute("proveedor",proveedorRepo.findById(facturaRepo.findById(factura.getIdFactura()).get().getProveedor().getIdProveedor()).get());
		model.addAttribute("facturas",proveedorRepo.findById(facturaRepo.findById(factura.getIdFactura()).get().getProveedor().getIdProveedor()).get().getListaFacturas());
		float deuda = 0;
		float pagado = 0;
		for(Factura f: proveedorRepo.findById(facturaRepo.findById(factura.getIdFactura()).get().getProveedor().getIdProveedor()).get().getListaFacturas()) {
			if(f.isEliminada() == false) {
				if(f.getEstadoFactura().getIdEstadoFactura() == 2F) {
					deuda = deuda + f.getTotalFactura();
				} else {
					pagado = pagado + f.getTotalFactura();
				}
			}
		}
		this.total = 0;	
		if(this.listaInsumos.equals(this.listaInsumosViejos) &&
		factura.getEstadoFactura().getIdEstadoFactura() == facturaRepo.findById(factura.getIdFactura()).get().getEstadoFactura().getIdEstadoFactura() &&
		factura.getNumeroFactura() == facturaRepo.findById(factura.getIdFactura()).get().getNumeroFactura() &&
		factura.getFecha().toString().equals(facturaRepo.findById(factura.getIdFactura()).get().getFecha().toString()) ) {	
			model.addAttribute("showWarningIgual",true);
			model.addAttribute("pagado",pagado);
			model.addAttribute("deuda",deuda);
			return "detalle";								//cambiovista			
		} else {
			this.listaInsumosViejos.clear(); 								//limpio listaInsumos viejos
		for(FacturaInsumo i: facturaRepo.findById(idFactura).get().getListaFacturaInsumo()) {
			facturaInsumo.delete(i);
		};
		Proveedor p = new Proveedor();
		p = facturaRepo.findById(this.idFactura).get().getProveedor();
		if(factura.getEstadoFactura().getIdEstadoFactura() == 1L) {
			factura.setFechaPago(new SimpleDateFormat("yyyy-MM-dd").format(myDate));
		}
		factura.setProveedor(p);
		for(Insumo x: this.listaInsumos) {
			 this.total = this.total + (x.getPrecio() * x.getCantidadMinima());
		};
		factura.setTotalFactura(this.total);
		facturaRepo.save(factura);
		for(Insumo x : this.listaInsumos) {
			FacturaInsumo fi = new FacturaInsumo();
			fi.setCantidadInsumo(x.getCantidadMinima());
			fi.setFactura(factura);
			fi.setInsumo(x);
			fi.setPrecioParcialInsumo(x.getPrecio()); 					// precio parcial
			facturaInsumo.save(fi);
		};
		model.addAttribute("showUpdate",true);							//cambiovista		
		model.addAttribute("pagado",pagado);
		model.addAttribute("deuda",deuda);
		return "detalle";
		}
	}
	
	@RequestMapping(value="/proveedores", method = RequestMethod.GET)			//proveedores get, es la lista de proveedores
	public String proveedores(Model model) {
		for(Proveedor p: proveedorRepo.findAll()) {
			p.setEstadoProveedor(false);
			proveedorRepo.save(p);
			for(Factura f: p.getListaFacturas()) {
				if(f.getEstadoFactura().getIdEstadoFactura() == 2L && f.isEliminada() == false) {
					p.setEstadoProveedor(true);
					proveedorRepo.save(p);
					break;
				}
			}
		}
		model.addAttribute("proveedores", proveedorRepo.findAll());
		return "proveedores";
	}
	
	@RequestMapping(value="/resumenFactura/{idProveedor}/factura/{id}",method=RequestMethod.GET)			//resumenFactura get, es el resumen de una factura en particular
	public String resumenFactura(@PathVariable("idProveedor") long idProveedor, @PathVariable("id") long idFactura, Model model) {
		List<Insumo> listaInsumos = new ArrayList<Insumo>();			// lista local
		for(FacturaInsumo fi : facturaRepo.findById(idFactura).get().getListaFacturaInsumo()) {
			Insumo insumo = new Insumo();
			insumo = fi.getInsumo();
			insumo.setPrecio(fi.getPrecioParcialInsumo());
			insumo.setCantidadMinima(fi.getCantidadInsumo());
			listaInsumos.add(insumo);
		}
		model.addAttribute("factura",facturaRepo.findById(idFactura).get());
		model.addAttribute("proveedor",proveedorRepo.findById(idProveedor).get());
		model.addAttribute("insumos",listaInsumos);
		return "resumen_factura";
	}

}
