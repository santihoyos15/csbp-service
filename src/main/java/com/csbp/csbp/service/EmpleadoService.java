package com.csbp.csbp.service;

import com.csbp.csbp.dao.EmpleadoRepository;
import com.csbp.csbp.domain.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> getAll() {
        return (List<Empleado>) empleadoRepository.findAll();
    }
}
