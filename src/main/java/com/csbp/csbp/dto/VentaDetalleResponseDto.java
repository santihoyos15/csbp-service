package com.csbp.csbp.dto;

import java.util.List;

public class VentaDetalleResponseDto {
    private String cliente;
    private List<ProductoDetalleDto> productos;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<ProductoDetalleDto> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDetalleDto> productos) {
        this.productos = productos;
    }
}
