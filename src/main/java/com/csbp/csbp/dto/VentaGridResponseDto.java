package com.csbp.csbp.dto;

import java.util.Date;

public class VentaGridResponseDto {
    private String codigo;
    private Date fecha;
    private double total;
    private String cliente;

    public VentaGridResponseDto(String codigo, Date fecha, double total, String cliente) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.total = total;
        this.cliente = cliente;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
            return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
