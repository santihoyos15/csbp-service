package com.csbp.csbp.controller;

import com.csbp.csbp.domain.Empleado;
import com.csbp.csbp.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("empleado/")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/all")
    public List<Empleado> getAll() {
        return empleadoService.getAll();
    }
}
