package com.csbp.csbp.controller;

import com.csbp.csbp.domain.Venta;
import com.csbp.csbp.dto.VentaDto;
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
    public List<Venta> getAll() {
        return ventaService.getAll();
    }

    @PostMapping()
    @ResponseBody
    public Venta create(@RequestBody VentaDto ventaDto) {
        return ventaService.save(ventaDto);
    }

}
