package com.aegis.sales_apps.service;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 10/2/2023 14:57
@Last Modified 10/2/2023 14:57
Version 1.0
*/


import com.aegis.sales_apps.model.request.AuthRequest;
import com.aegis.sales_apps.model.request.LoginRequest;
import com.aegis.sales_apps.model.response.LoginResponse;
import com.aegis.sales_apps.model.response.RegisterResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    RegisterResponse register(AuthRequest authRequest);

    LoginResponse login(LoginRequest loginRequestequesta);

}
