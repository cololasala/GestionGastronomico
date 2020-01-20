package com.LucianoLasala.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LucianoLasala.gestion.model.Factura;


public interface IFactura extends JpaRepository<Factura, Long>{

}
