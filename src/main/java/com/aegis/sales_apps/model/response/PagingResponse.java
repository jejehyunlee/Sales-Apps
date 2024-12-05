package com.aegis.sales_apps.model.response;

import com.aegis.sales_apps.entity.Product;
import lombok.*;

import java.util.List;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 9/25/2023 10:36
@Last Modified 9/25/2023 10:36
Version 1.0
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PagingResponse {
    private Integer curentPage;
    private Integer totalPage;
    private Integer size;
}
