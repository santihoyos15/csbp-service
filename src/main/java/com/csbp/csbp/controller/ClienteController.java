package com.csbp.csbp.controller;

import com.csbp.csbp.domain.Cliente;
import com.csbp.csbp.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cliente/")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/all")
    public List<Cliente> getAll() {
        return clienteService.getAll();
    }
}
