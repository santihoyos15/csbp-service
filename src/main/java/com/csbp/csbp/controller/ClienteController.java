package com.csbp.csbp.controller;

import com.csbp.csbp.domain.Cliente;
import com.csbp.csbp.dto.ClienteDto;
import com.csbp.csbp.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO Login, sign up
// TODO endpoint detalle venta
// TODO Codigo autogenerado
// TODO email should be unique throuout Empleado and Cliente

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/all")
    public List<Cliente> getAll() {
        return clienteService.getAll();
    }

    @PostMapping()
    @ResponseBody
    public Cliente create(@RequestBody ClienteDto clienteDto) {
        return clienteService.save(clienteDto);
    }

    @PutMapping("/{clienteId}")
    @ResponseBody
    public Cliente edit(@PathVariable Long clienteId, @RequestBody ClienteDto clienteDto) {
        clienteDto.setId(clienteId);
        return clienteService.edit(clienteDto);
    }

    @DeleteMapping("/{clienteId}")
    @ResponseBody
    public Cliente delete(@PathVariable Long clienteId, @RequestBody ClienteDto clienteDto) {
        clienteDto.setId(clienteId);
        return clienteService.delete(clienteDto);
    }
}
