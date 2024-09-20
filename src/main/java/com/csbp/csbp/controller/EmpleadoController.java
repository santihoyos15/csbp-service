package com.csbp.csbp.controller;

import com.csbp.csbp.domain.Empleado;
import com.csbp.csbp.dto.EmpleadoDto;
import com.csbp.csbp.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/all")
    public List<Empleado> getAll() {
        return empleadoService.getAll();
    }

    @PostMapping()
    @ResponseBody
    public Empleado create(@RequestBody EmpleadoDto empleadoDto) {
        return empleadoService.save(empleadoDto);
    }

    @PutMapping("/{empleadoId}")
    @ResponseBody
    public Empleado edit(@PathVariable Long empleadoId, @RequestBody EmpleadoDto empleadoDto) {
        empleadoDto.setId(empleadoId);
        return empleadoService.edit(empleadoDto);
    }

    @DeleteMapping("/{empleadoId}")
    @ResponseBody
    public Empleado delete(@PathVariable Long empleadoId, @RequestBody EmpleadoDto empleadoDto) {
        empleadoDto.setId(empleadoId);
        return empleadoService.delete(empleadoDto);
    }
}
