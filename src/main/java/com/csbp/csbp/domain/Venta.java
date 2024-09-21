package com.csbp.csbp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "empleadoId")
    @JsonIgnore
    private User empleado;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    @JsonIgnore
    private User cliente;

    @ManyToMany
    @JoinTable(
        name = "ventaProducto",
        joinColumns = @JoinColumn(name = "ventaId"),
        inverseJoinColumns = @JoinColumn(name = "productoId")
    )
    private List<Producto> productos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public User getEmpleado() {
        return empleado;
    }

    public void setEmpleado(User empleado) {
        this.empleado = empleado;
    }

    public User getCliente() {
        return cliente;
    }

    public void setCliente(User cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
