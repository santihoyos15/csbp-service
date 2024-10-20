package com.csbp.csbp.service;

import com.csbp.csbp.dao.UserRepository;
import com.csbp.csbp.domain.User;
import com.csbp.csbp.dto.AuthRequestDto;
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
    @Autowired
    private AuthService authService;

    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    public String save(UserDto userDto) {
        var user = new User();
        user.setDni(userDto.getDni());
        user.setNombre(userDto.getNombre());
        user.setPrimerApellido(userDto.getPrimerApellido());
        user.setSegundoApellido(userDto.getSegundoApellido());
        user.setEmail(userDto.getEmail());
        user.setActive(userDto.isActive());

        userRepository.save(user);

        return "Usuario guardado con exito";
    }

    public User edit(UserDto userDto) {
        var userOptional = userRepository.findById(userDto.getId());

        if (userOptional.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "usuario no existe");
        }

        var user = userOptional.get();

        user.setNombre(userDto.getNombre());
        user.setDni(userDto.getDni());
        user.setNombre(userDto.getNombre());
        user.setPrimerApellido(userDto.getPrimerApellido());
        user.setSegundoApellido(userDto.getSegundoApellido());
        user.setEmail(userDto.getEmail());
        user.setActive(userDto.isActive());

        return userRepository.save(user);
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
