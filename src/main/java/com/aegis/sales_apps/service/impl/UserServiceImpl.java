package com.aegis.sales_apps.service.impl;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 9/22/2023 15:29
@Last Modified 9/22/2023 15:29
Version 1.0
*/


import com.aegis.sales_apps.entity.User;
import com.aegis.sales_apps.model.request.UserRequest;
import com.aegis.sales_apps.repository.UserRepository;
import com.aegis.sales_apps.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public List<User> getAll(String mesage, HttpStatus httpStatus) {
        return userRepository.findAll();
    }


    @Transactional(rollbackOn = Exception.class)
    @Override
    public User update(UserRequest request) {
        User user = User.builder()
                .idUser(request.getIdUser())
                .name(request.getName())
                .address(request.getAddress())
                .mobilePhoneCustomer(request.getMobilePhone())
                .email(request.getEmail())
                .build();
        return userRepository.saveAndFlush(user);

    }


    @Override
    public User deleteById(String id) {
        userRepository.deleteById(id);
        return null;
    }
}