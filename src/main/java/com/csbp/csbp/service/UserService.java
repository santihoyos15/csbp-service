package com.csbp.csbp.service;

import com.csbp.csbp.dao.UserRepository;
import com.csbp.csbp.domain.User;
import com.csbp.csbp.dto.ApiResponse;
import com.csbp.csbp.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    public ApiResponse save(UserDto userDto) {
        User userByDni = userRepository.findByDni(userDto.getDni());

        if (userByDni != null) {
            return new ApiResponse(false, "DNI en uso");
        }

        User userByEmail = userRepository.findByEmail(userDto.getEmail());

        if (userByEmail != null) {
            return new ApiResponse(false, "Correo en uso");
        }

        var user = new User();
        user.setDni(userDto.getDni());
        user.setNombre(userDto.getNombre());
        user.setPrimerApellido(userDto.getPrimerApellido());
        user.setSegundoApellido(userDto.getSegundoApellido());
        user.setEmail(userDto.getEmail());
        user.setActive(userDto.isActive());
        user.setAdmin(userDto.isAdmin());

        userRepository.save(user);

        return new ApiResponse(true, "Usuario creado con exito");
    }

    public ApiResponse edit(UserDto userDto) {
        var userOptional = userRepository.findById(userDto.getId());

        if (userOptional.isEmpty()) {
            return new ApiResponse(false, "Usuario no existe");
        }

        var user = userOptional.get();

        user.setNombre(userDto.getNombre());
        user.setDni(userDto.getDni());
        user.setNombre(userDto.getNombre());
        user.setPrimerApellido(userDto.getPrimerApellido());
        user.setSegundoApellido(userDto.getSegundoApellido());
        user.setEmail(userDto.getEmail());
        user.setActive(userDto.isActive());
        user.setAdmin(userDto.isAdmin());

        userRepository.save(user);

        return new ApiResponse(true, "Usuario actualizado con exito");
    }

    public User delete(UserDto userDto) {
        var empleadoOptional = userRepository.findById(userDto.getId());

        if (empleadoOptional.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Usuario no existe");
        }

        userRepository.delete(empleadoOptional.get());

        return empleadoOptional.get();
    }

    public List<User> list() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }
}
