package com.aegis.sales_apps.controller;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 10/3/2023 09:12
@Last Modified 10/3/2023 09:12
Version 1.0
*/

import com.aegis.sales_apps.model.request.AuthRequest;
import com.aegis.sales_apps.model.request.LoginRequest;
import com.aegis.sales_apps.model.response.LoginResponse;
import com.aegis.sales_apps.model.response.RegisterResponse;
import com.aegis.sales_apps.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
            return authService.login(request);
    }


}

