package com.actividad1.aws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actividad1.aws.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    boolean existsByCodigo(String codigo);

}