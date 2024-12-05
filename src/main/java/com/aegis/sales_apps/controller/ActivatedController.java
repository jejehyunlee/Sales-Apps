package com.aegis.sales_apps.controller;

import com.aegis.sales_apps.model.response.ActivatedResponse;
import com.aegis.sales_apps.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class ActivatedController {

        private final AuthServiceImpl userActivationService;

        @PutMapping("/activate-user")
        @PreAuthorize("hasRole('ADMIN')") // Hanya admin yang dapat mendaftarkan kasir
        public ActivatedResponse activateUser(@RequestParam String email) {
            return userActivationService.activateUser(email);
        }
    }


