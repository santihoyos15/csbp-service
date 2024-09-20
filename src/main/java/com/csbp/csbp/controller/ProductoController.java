package com.csbp.csbp.controller;

import com.csbp.csbp.domain.Producto;
import com.csbp.csbp.dto.ProductoDto;
import com.csbp.csbp.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/all")
    public List<Producto> getAll() {
        return productoService.getAll();
    }

    @PostMapping()
    @ResponseBody
    public Producto create(@RequestBody ProductoDto productoDto) {
        return productoService.save(productoDto);
    }

    @PutMapping("/{productoId}")
    @ResponseBody
    public Producto edit(@PathVariable Long productoId, @RequestBody ProductoDto productoDto) {
        productoDto.setId(productoId);
        return productoService.edit(productoDto);
    }

    @DeleteMapping("/{productoId}")
    @ResponseBody
    public Producto delete(@PathVariable Long productoId, @RequestBody ProductoDto productoDto) {
        productoDto.setId(productoId);
        return productoService.delete(productoDto);
    }
}
