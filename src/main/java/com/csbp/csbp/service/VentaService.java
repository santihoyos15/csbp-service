package com.csbp.csbp.service;

import com.csbp.csbp.dao.ProductoRepository;
import com.csbp.csbp.dao.VentaRepository;
import com.csbp.csbp.domain.*;
import com.csbp.csbp.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    public List<VentaGridResponseDto> getAll() {
        return ((List<Venta>) ventaRepository.findAll()).stream()
                .map(venta -> new VentaGridResponseDto(
                        venta.getCodigo(),
                        venta.getFecha(),
                        venta.getTotal(),
                        venta.getCliente().getNombre() + " " + venta.getCliente().getPrimerApellido() + " " + venta.getCliente().getSegundoApellido()
                ))
                .collect(Collectors.toList());
    }

    public Venta save(VentaDto ventaDto) {
        User empleado = userService.findById(ventaDto.getEmpleadoId())
                .orElseThrow(() -> new RuntimeException("Empleado no existe"));

        Cliente cliente = clienteService.findById(ventaDto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no existe "));

        Venta lastVenta = ventaRepository.findFirstByOrderByIdDesc();

        long lastVentaId = lastVenta != null ? lastVenta.getId() : 1;

        Venta venta = new Venta();
        venta.setEmpleado(empleado);
        venta.setCliente(cliente);
        venta.setFecha(new Date());
        venta.setVentaProductos(new ArrayList<>());
        venta.setCodigo("" + empleado.getId() + lastVentaId);

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

    public VentaDetalleResponseDto getDetalle(String codigo) {
        Optional<Venta> ventaOptional = ventaRepository.findByCodigo(codigo);

        if (ventaOptional.isEmpty()) {
            throw new RuntimeException("Venta no existe");
        }

        Venta venta = ventaOptional.get();

        var response = new VentaDetalleResponseDto();

        response.setCliente(venta.getCliente().getNombre() + venta.getCliente().getNombre() + venta.getCliente().getSegundoApellido());

        List<VentaProducto> ventaProductos = venta.getVentaProductos();

        List<ProductoDetalleDto> productoDetalles = new ArrayList<>();

        ventaProductos.forEach(vp -> {
            productoDetalles.add(new ProductoDetalleDto(
                vp.getProducto().getNombre(),
                vp.getCantidad(),
                vp.getProducto().getCosto() * vp.getCantidad()
            ));
        });

        response.setProductos(productoDetalles);

        return response;
    }

}
