package com.csbp.csbp.service;

import com.csbp.csbp.dao.ClienteRepository;
import com.csbp.csbp.dao.EmpleadoRepository;
import com.csbp.csbp.dao.ProductoRepository;
import com.csbp.csbp.dao.VentaRepository;
import com.csbp.csbp.domain.Cliente;
import com.csbp.csbp.domain.Empleado;
import com.csbp.csbp.domain.Producto;
import com.csbp.csbp.domain.Venta;
import com.csbp.csbp.dto.VentaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProductoRepository productoRepository;


    public List<Venta> getAll() {
        return (List<Venta>) ventaRepository.findAll();
    }

    public Venta save(VentaDto ventaDto) {
        Empleado empleado = empleadoRepository.findById(ventaDto.getEmpleadoId())
                .orElseThrow(() -> new RuntimeException("Empleado no existe"));

        Cliente cliente = clienteRepository.findById(ventaDto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no existe "));

        List<Producto> productos = (List<Producto>) productoRepository.findAllById(ventaDto.getProductos());

        Venta venta = new Venta();
        venta.setEmpleado(empleado);
        venta.setCliente(cliente);
        venta.setProductos(productos);
        venta.setFecha(ventaDto.getFecha());

        double total = productos.stream().mapToDouble(Producto::getCosto).sum();
        venta.setTotal(total);

        return ventaRepository.save(venta);
    }
}
