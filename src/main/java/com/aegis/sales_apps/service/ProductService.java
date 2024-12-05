package com.aegis.sales_apps.service;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 9/21/2023 14:36
@Last Modified 9/21/2023 14:36
Version 1.0
*/


import com.aegis.sales_apps.entity.Product;
import com.aegis.sales_apps.model.request.ProductReportRequest;
import com.aegis.sales_apps.model.request.ProductRequest;
import com.aegis.sales_apps.model.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Product create(Product product);
    Product getById(String id);
    List<Product> getAll();
    Product update(Product product);
    void deleteById(String id);
    ProductResponse createProduct(ProductRequest request);

    List<ProductReportRequest> getProductReportData();

    Page<ProductResponse> searchNameOrPrice(String name, Long maxPrice, Integer page, Integer size);
}
