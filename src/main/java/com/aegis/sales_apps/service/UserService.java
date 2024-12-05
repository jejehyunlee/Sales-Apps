package com.aegis.sales_apps.service;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 9/22/2023 15:27
@Last Modified 9/22/2023 15:27
Version 1.0
*/


import com.aegis.sales_apps.entity.User;
import com.aegis.sales_apps.model.request.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User getByEmail(String email);

    List<User> getAll(String mesage, HttpStatus httpStatus);

    User update(UserRequest userRequest);

    User deleteById(String id);
}
