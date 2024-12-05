package com.aegis.sales_apps.model.response;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 10/2/2023 13:09
@Last Modified 10/2/2023 13:09
Version 1.0
*/

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {

    private String message;

    private String email;

    private List<String> roles;

    private String token;
}
