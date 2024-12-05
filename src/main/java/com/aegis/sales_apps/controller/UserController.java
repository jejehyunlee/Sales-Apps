package com.aegis.sales_apps.controller;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 9/22/2023 15:39
@Last Modified 9/22/2023 15:39
Version 1.0
*/



import com.aegis.sales_apps.entity.User;
import com.aegis.sales_apps.model.request.AuthRequest;
import com.aegis.sales_apps.model.request.UserRequest;
import com.aegis.sales_apps.model.response.CommonResponse;
import com.aegis.sales_apps.model.response.DeleteResponse;
import com.aegis.sales_apps.model.response.GetAllResponse;
import com.aegis.sales_apps.model.response.RegisterResponse;
import com.aegis.sales_apps.service.AuthService;
import com.aegis.sales_apps.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/admin")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    private final AuthService authService;


    @PostMapping("/create-user")
//    @PreAuthorize("hasRole('ADMIN')") // Hanya admin yang dapat mendaftarkan kasir
    public RegisterResponse registerUser(@RequestBody AuthRequest request) {
        return authService.register(request);
    }


    @GetMapping(path = "/all")
    public GetAllResponse<User> getAll() {
        List<User> users = userServiceImpl.getAll("OKE", HttpStatus.OK);
        return GetAllResponse
                .<User>builder()
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .message("Semua Data Ditemukan")
                .dataList(users)
                .build();
    }



    @PutMapping(
            path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CommonResponse<User> update(@RequestBody UserRequest userRequest) {
        User user = userServiceImpl.update(userRequest);
        return CommonResponse
                .<User>builder()
                .httpStatus(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .message("Success Update")
                .data(user)
                .build();
    }



    @DeleteMapping(value = "/delete/{id}")
    public DeleteResponse userDelete(@PathVariable String id) {
            return DeleteResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .data(id)
                    .message("Berhasil dihapus")
                    .build();
    }

}
