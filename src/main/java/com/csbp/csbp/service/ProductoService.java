package com.csbp.csbp.service;

import com.csbp.csbp.dao.ProductoRepository;
import com.csbp.csbp.domain.Producto;
import com.csbp.csbp.dto.ProductoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAll() {
        return (List<Producto>) productoRepository.findAll();
    }

    public Producto save(ProductoDto productoDto) {
        var producto = new Producto();
        producto.setNombre(productoDto.getNombre());
        producto.setMarca(productoDto.getMarca());
        producto.setCosto(productoDto.getCosto());
        producto.setCantidad(productoDto.getCantidad());
        producto.setActivo(productoDto.isActivo());

        return productoRepository.save(producto);
    }

    public Producto edit(ProductoDto productoDto) {
        var productoOptional = productoRepository.findById(productoDto.getId());

        if (productoOptional.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Producto no existe");
        }

        var producto = productoOptional.get();

        producto.setNombre(productoDto.getNombre());
        producto.setMarca(productoDto.getMarca());
        producto.setCosto(productoDto.getCosto());
        producto.setCantidad(productoDto.getCantidad());
        producto.setActivo(productoDto.isActivo());

        return productoRepository.save(producto);
    }

    public Producto delete(ProductoDto productoDto) {
        var productoOptional = productoRepository.findById(productoDto.getId());

        if (productoOptional.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Producto no existe");
        }

        productoRepository.delete(productoOptional.get());

        return productoOptional.get();
    }
}
