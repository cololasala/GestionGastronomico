package com.LucianoLasala.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LucianoLasala.gestion.model.Categoria;

public interface ICategoria extends JpaRepository<Categoria, Long>  {

}
