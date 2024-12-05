package com.aegis.sales_apps.service;


import com.aegis.sales_apps.model.request.AuthRequest;
import org.springframework.http.ResponseEntity;

public interface AuthValidation {
    ResponseEntity<Object> validateRegisterRequest(AuthRequest authRequest);

}
