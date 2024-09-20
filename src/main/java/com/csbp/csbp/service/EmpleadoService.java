package com.csbp.csbp.service;

import com.csbp.csbp.dao.EmpleadoRepository;
import com.csbp.csbp.domain.Empleado;
import com.csbp.csbp.dto.EmpleadoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> getAll() {
        return (List<Empleado>) empleadoRepository.findAll();
    }

    public Empleado save(EmpleadoDto empleadoDto) {
        var empleado = new Empleado();
        empleado.setNombre(empleadoDto.getNombre());
        empleado.setIdentificacion(empleadoDto.getIdentificacion());
        empleado.setNombre(empleadoDto.getNombre());
        empleado.setPrimerApellido(empleadoDto.getPrimerApellido());
        empleado.setSegundoApellido(empleadoDto.getSegundoApellido());
        empleado.setFechaNacimiento(empleadoDto.getFechaNacimiento());
        empleado.setEmail(empleadoDto.getEmail());
        empleado.setActive(empleadoDto.isActive());

        return empleadoRepository.save(empleado);
    }

    public Empleado edit(EmpleadoDto empleadoDto) {
        var empleadoOptional = empleadoRepository.findById(empleadoDto.getId());

        if (empleadoOptional.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Empleado no existe");
        }

        var empleado = empleadoOptional.get();

        empleado.setNombre(empleadoDto.getNombre());
        empleado.setIdentificacion(empleadoDto.getIdentificacion());
        empleado.setNombre(empleadoDto.getNombre());
        empleado.setPrimerApellido(empleadoDto.getPrimerApellido());
        empleado.setSegundoApellido(empleadoDto.getSegundoApellido());
        empleado.setFechaNacimiento(empleadoDto.getFechaNacimiento());
        empleado.setEmail(empleadoDto.getEmail());
        empleado.setActive(empleadoDto.isActive());

        return empleadoRepository.save(empleado);
    }

    public Empleado delete(EmpleadoDto empleadoDto) {
        var empleadoOptional = empleadoRepository.findById(empleadoDto.getId());

        if (empleadoOptional.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Empleado no existe");
        }

        empleadoRepository.delete(empleadoOptional.get());

        return empleadoOptional.get();
    }
}
