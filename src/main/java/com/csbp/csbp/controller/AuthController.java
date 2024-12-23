package com.csbp.csbp.controller;

import com.csbp.csbp.domain.User;
import com.csbp.csbp.dto.ApiResponse;
import com.csbp.csbp.dto.AuthRequestDto;
import com.csbp.csbp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @ResponseBody
    public ApiResponse register(@RequestBody AuthRequestDto authRequest) {
        return authService.registerUser(authRequest);
    }

    @PostMapping("/login")
    public User login(@RequestBody AuthRequestDto authRequest) {
        return authService.login(authRequest);
    }
}