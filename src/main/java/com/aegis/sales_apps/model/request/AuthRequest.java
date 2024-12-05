package com.aegis.sales_apps.model.request;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 10/2/2023 11:45
@Last Modified 10/2/2023 11:45
Version 1.0
*/

import com.aegis.sales_apps.entity.constant.ERole;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AuthRequest {

    @Email(message = "email not valid")
    @NotBlank
    private String email;
    @NotBlank(message = "Password tidak boleh kosong")
    private String password;
    @NotBlank(message = "Nama tidak boleh kosong")
    private String name;
    @NotBlank(message = "Alamat tidak boleh kosong")
    private String address;
    @NotBlank(message = "No. HP tidak boleh kosong")
    private String mobilePhone;

    private String role;

    @Column(name = "is_active")
    private boolean isActive;

}
