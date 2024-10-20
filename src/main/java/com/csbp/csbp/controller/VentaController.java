package com.csbp.csbp.controller;

import com.csbp.csbp.dto.VentaDetalleResponseDto;
import com.csbp.csbp.dto.VentaDto;
import com.csbp.csbp.dto.VentaGridResponseDto;
import com.csbp.csbp.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping("/all")
    public List<VentaGridResponseDto> getAll() {
        return ventaService.getAll();
    }

    @PostMapping()
    @ResponseBody
    public void create(@RequestBody VentaDto ventaDto) {
        ventaService.save(ventaDto);
    }

    @GetMapping("/{codigo}/detalle")
    @ResponseBody
    public VentaDetalleResponseDto getDetalle (@PathVariable String codigo) {
        return ventaService.getDetalle(codigo);
    }
}
