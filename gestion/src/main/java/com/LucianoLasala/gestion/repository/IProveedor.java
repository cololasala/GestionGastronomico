package com.LucianoLasala.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.LucianoLasala.gestion.model.Proveedor;

public interface IProveedor extends JpaRepository<Proveedor, Long>{

	@Query(nativeQuery=true, value="SELECT * FROM gestion.proveedor where id_proveedor = ?1")
	Proveedor buscarPorId(Long idProveedor);
	
}
