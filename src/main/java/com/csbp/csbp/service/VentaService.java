package com.csbp.csbp.service;

import com.csbp.csbp.dao.ProductoRepository;
import com.csbp.csbp.dao.VentaRepository;
import com.csbp.csbp.domain.*;
import com.csbp.csbp.dto.ProductoDto;
import com.csbp.csbp.dto.VentaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProductoRepository productoRepository;


    public List<Venta> getAll() {
        return (List<Venta>) ventaRepository.findAll();
    }

    public Venta save(VentaDto ventaDto) {
        User empleado = userService.findById(ventaDto.getEmpleadoId())
                .orElseThrow(() -> new RuntimeException("Empleado no existe"));

        Cliente cliente = clienteService.findById(ventaDto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no existe "));

        Venta venta = new Venta();
        venta.setEmpleado(empleado);
        venta.setCliente(cliente);
        venta.setFecha(new Date());
        venta.setVentaProductos(new ArrayList<>());

        double total = 0;
        for (ProductoDto productoDto : ventaDto.getProductos()) {
            Producto producto = productoRepository.findById(productoDto.getId())
                    .orElseThrow(() -> new RuntimeException("Producto no existe"));

            VentaProducto ventaProducto = new VentaProducto();
            ventaProducto.setProducto(producto);
            ventaProducto.setCantidad(productoDto.getCantidad());
            ventaProducto.setVenta(venta);

            venta.getVentaProductos().add(ventaProducto);

            total += producto.getCosto() * productoDto.getCantidad();
        }

        venta.setTotal(total);
        return ventaRepository.save(venta);
    }
}
