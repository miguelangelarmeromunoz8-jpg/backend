package com.actividad1.aws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.actividad1.aws.model.Producto;
import com.actividad1.aws.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    public List<Producto> listar() {
        return repository.findAll();
    }

    public Producto guardar(Producto producto) {

        if(repository.existsByCodigo(producto.getCodigo())) {
            throw new RuntimeException("El codigo ya existe");
        }

        return repository.save(producto);
    }

    public Producto buscar(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Producto actualizar(Long id, Producto producto) {

        Producto p = buscar(id);

        if(p == null) {
            return null;
        }

        p.setCodigo(producto.getCodigo());
        p.setNombre(producto.getNombre());
        p.setDescripcion(producto.getDescripcion());
        p.setPrecio(producto.getPrecio());
        p.setCantidad(producto.getCantidad());
        p.setEstado(producto.getEstado());

        return repository.save(p);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}