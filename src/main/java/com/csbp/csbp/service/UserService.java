package com.csbp.csbp.service;

import com.csbp.csbp.dao.UserRepository;
import com.csbp.csbp.domain.User;
import com.csbp.csbp.dto.AuthRequestDto;
import com.csbp.csbp.dto.ListUsersDto;
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

    public String save(AuthRequestDto userDto) {
        return authService.registerUser(userDto);
    }

    public User edit(UserDto userDto) {
        var userOptional = userRepository.findById(userDto.getId());

        if (userOptional.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "usuario no existe");
        }

        var user = userOptional.get();

        user.setNombre(userDto.getNombre());
        user.setIdentificacion(userDto.getIdentificacion());
        user.setNombre(userDto.getNombre());
        user.setPrimerApellido(userDto.getPrimerApellido());
        user.setSegundoApellido(userDto.getSegundoApellido());
        user.setFechaNacimiento(userDto.getFechaNacimiento());
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

    public List<User> list(ListUsersDto listUsersDto) {
        if (listUsersDto.getRoleId() != null) {
            return userRepository.findAllByRole(listUsersDto.getRoleId());
        }

        return (List<User>) userRepository.findAll();
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }
}
