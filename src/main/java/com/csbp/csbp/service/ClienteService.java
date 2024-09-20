package com.csbp.csbp.service;

import com.csbp.csbp.dao.ClienteRepository;
import com.csbp.csbp.domain.Cliente;
import com.csbp.csbp.dto.ClienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAll() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    public Cliente save(ClienteDto clienteDto) {
        var cliente = new Cliente();
        cliente.setNombre(clienteDto.getNombre());
        cliente.setIdentificacion(clienteDto.getIdentificacion());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setPrimerApellido(clienteDto.getPrimerApellido());
        cliente.setSegundoApellido(clienteDto.getSegundoApellido());
        cliente.setFechaNacimiento(clienteDto.getFechaNacimiento());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setActive(clienteDto.isActive());

        return clienteRepository.save(cliente);
    }

    public Cliente edit(ClienteDto clienteDto) {
        var clienteOptional = clienteRepository.findById(clienteDto.getId());

        if (clienteOptional.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Cliente no existe");
        }

        var cliente = clienteOptional.get();

        cliente.setNombre(clienteDto.getNombre());
        cliente.setIdentificacion(clienteDto.getIdentificacion());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setPrimerApellido(clienteDto.getPrimerApellido());
        cliente.setSegundoApellido(clienteDto.getSegundoApellido());
        cliente.setFechaNacimiento(clienteDto.getFechaNacimiento());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setActive(clienteDto.isActive());

        return clienteRepository.save(cliente);
    }

    public Cliente delete(ClienteDto clienteDto) {
        var clienteOptional = clienteRepository.findById(clienteDto.getId());

        if (clienteOptional.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Cliente no existe");
        }

        clienteRepository.delete(clienteOptional.get());

        return clienteOptional.get();
    }
}