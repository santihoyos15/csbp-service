package com.csbp.csbp.service;

import com.csbp.csbp.dao.UserRepository;
import com.csbp.csbp.domain.User;
import com.csbp.csbp.dto.ApiResponse;
import com.csbp.csbp.dto.AuthRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApiResponse registerUser(AuthRequestDto authRequest) {
        User userByEmail = userRepository.findByEmail(authRequest.getEmail());

        if (userByEmail != null) {
            return new ApiResponse(false, "Correo en uso");
        }

        User userByDni = userRepository.findByDni(authRequest.getDni());

        if (userByDni != null) {
            return new ApiResponse(false, "DNI en uso");
        }

        User user = new User();
        user.setEmail(authRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        user.setDni(authRequest.getDni());
        user.setNombre(authRequest.getNombre());
        user.setPrimerApellido(authRequest.getPrimerApellido());
        user.setSegundoApellido(authRequest.getSegundoApellido());
        user.setActive(true);
        user.setAdmin(false);

        userRepository.save(user);

        return new ApiResponse(true, "Usuario registrado con exito");
    }

    public User login(AuthRequestDto authRequest) {
        User user = userRepository.findByEmail(authRequest.getEmail());
        if (user != null && passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            return user;
        }

        throw new RuntimeException("Credenciales invalidas");
    }
}