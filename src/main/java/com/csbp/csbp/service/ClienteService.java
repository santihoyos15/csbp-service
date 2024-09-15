package com.csbp.csbp.service;

import com.csbp.csbp.dao.ClienteRepository;
import com.csbp.csbp.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAll() {
        return (List<Cliente>) clienteRepository.findAll();
    }
}