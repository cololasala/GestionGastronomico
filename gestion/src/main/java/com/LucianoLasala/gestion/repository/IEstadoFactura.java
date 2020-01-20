package com.LucianoLasala.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LucianoLasala.gestion.model.EstadoFactura;


public interface IEstadoFactura extends JpaRepository<EstadoFactura, Long>{

}
