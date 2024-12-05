package com.aegis.sales_apps.service.impl;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 10/2/2023 15:00
@Last Modified 10/2/2023 15:00
Version 1.0
*/

import com.aegis.sales_apps.config.LoggingConfig;
import com.aegis.sales_apps.entity.User;
import com.aegis.sales_apps.entity.Role;
import com.aegis.sales_apps.entity.UserCredential;
import com.aegis.sales_apps.entity.UserDetailImpl;
import com.aegis.sales_apps.entity.constant.ERole;
import com.aegis.sales_apps.model.request.AuthRequest;
import com.aegis.sales_apps.model.request.LoginRequest;
import com.aegis.sales_apps.model.response.ActivatedResponse;
import com.aegis.sales_apps.model.response.LoginResponse;
import com.aegis.sales_apps.model.response.RegisterResponse;
import com.aegis.sales_apps.repository.UserRepository;
import com.aegis.sales_apps.repository.UserCredentialRepository;
import com.aegis.sales_apps.security.BCryptUtils;
import com.aegis.sales_apps.security.JwtUtils;
import com.aegis.sales_apps.service.*;
import com.aegis.sales_apps.utils.LoggingFile;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final ValidationService validationService;
    private String[] strExceptionArr = new String[2];

    private final UserCredentialRepository userCredentialRepository;

    private final AuthenticationManager authenticationManager;

    private final BCryptUtils bCryptUtils;

    private final UserService userService;

    private final JwtUtils jwtUtils;

    private final RoleService roleService;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse register(AuthRequest authRequest) {
        validationService.validate(authRequest);
        try {

            String roleName = authRequest.getRole().toUpperCase();

            ERole roleEnum = ERole.valueOf(roleName);  // Mengkonversi string ke ERole enum

            // Jika role adalah ROLE_ADMIN, set isActive menjadi true
            boolean userActive = ERole.ROLE_ADMIN.equals(roleEnum);

            authRequest.setActive(userActive);

            System.out.println("ROLE AKTIF " + userActive);

            System.out.println("??????? "  + roleEnum);

            System.out.println(authRequest.isActive());

            Role role = roleService.getOrSave(roleEnum);


            UserCredential userCredential = UserCredential.builder()
                    .email(authRequest.getEmail())
                    .password(bCryptUtils.hashPassword(authRequest.getPassword()))
                    .roles(List.of(role))
                    .build();
            userCredentialRepository.saveAndFlush(userCredential);

            User user = User.builder()
                    .name(authRequest.getName())
                    .address(authRequest.getAddress())
                    .mobilePhoneCustomer(authRequest.getMobilePhone())
                    .email(authRequest.getEmail())
                    .isActive(userActive)
                    .userCredential(userCredential)
                    .build();
            userRepository.saveAndFlush(user);

            return RegisterResponse.builder()
                    .message("Register Succesed")
                    .email(userCredential.getEmail())
                    .build();

        } catch (DataIntegrityViolationException e){
            strExceptionArr[0] = "AuthServiceImpl Class";
            strExceptionArr[1] = "registerNewCustomer(RegisterCustomerRequest request) --- LINE 54";
            LoggingFile.exceptionStringz(strExceptionArr, e, LoggingConfig.getFlagLogging());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Already Exist");
        }

    }


    @Override
    public LoginResponse login(LoginRequest loginRequest) {

    try {


                User user = userRepository.findByEmail(loginRequest.getUsername())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password"));


                if (!user.isActive()) {

                    return LoginResponse.builder()
                            .email(user.getEmail() + " Is Not Active ")
                            .message("Sorry, You Not Active, Please call admin")
                            .build();

                }

                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                ));

                SecurityContextHolder.getContext().setAuthentication(authentication);

                UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();


                List<String> roles = (userDetail.getAuthorities() != null && !userDetail.getAuthorities().isEmpty())
                        ? userDetail.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
                        : Collections.emptyList(); // Menghindari null pointer

                String token = jwtUtils.generateToken(userDetail.getEmail());


                return LoginResponse.builder()
                        .message("Login Succesed")
                        .email(userDetail.getEmail())
                        .roles(roles)
                        .token(token)
                        .build();

        } catch (Exception e){
                return LoginResponse.builder()
                        .message("Username or Password Invalid")
                        .build();
                 }

    }

    public ActivatedResponse activateUser(String email) {
        // Cari pengguna berdasarkan email
        User user = userRepository.findByEmail(email).orElse(null);

        // Jika pengguna tidak ditemukan
        if (user == null) {
            return ActivatedResponse.builder()
                    .email(email)
                    .message("User not found")
                    .build();
        }

        // Jika pengguna sudah aktif
        if (user.isActive()) {
            return ActivatedResponse.builder()
                    .email(email)
                    .message("Email is already active")
                    .build();
        }

        // Set pengguna menjadi aktif
        user.setActive(true);
        userRepository.save(user);

        // Return response
        return ActivatedResponse.builder()
                .email(user.getEmail())
                .message("Account has been activated. Please log in again.")
                .build();
    }



}
