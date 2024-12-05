package com.aegis.sales_apps.model.request;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 9/22/2023 15:58
@Last Modified 9/22/2023 15:58
Version 1.0
*/


import com.aegis.sales_apps.entity.UserCredential;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"email", "first_name", "last_name", "profile_image"})// Menyembunyikan field yang null
public class UserRequest {

    private String idUser;

    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String mobilePhone;
    @NotBlank
    private String email;

    @OneToOne
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredential;


}
