package com.aegis.sales_apps.controller;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 9/21/2023 14:44
@Last Modified 9/21/2023 14:44
Version 1.0
*/

import com.aegis.sales_apps.entity.Product;
import com.aegis.sales_apps.model.request.ProductRequest;
import com.aegis.sales_apps.model.response.CommonResponse;
import com.aegis.sales_apps.model.response.PagingResponse;
import com.aegis.sales_apps.model.response.ProductResponse;
import com.aegis.sales_apps.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/product")
@PreAuthorize("hasRole('ADMIN')") // Hanya admin yang dapat mendaftarkan kasir
public class ProductController {

    private final ProductServiceImpl productService;

    @PostMapping(
            path = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CommonResponse<ProductResponse> create(@RequestBody ProductRequest request) {
        ProductResponse productResponse = productService.createProduct(request);
        return CommonResponse
                .<ProductResponse>builder()
                .httpStatus(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .message("Success Create")
                .data(productResponse)
                .build();
    }

    @GetMapping(value = "/get/{id}")
    public Product productGetId(@PathVariable String id) {
        return productService.getById(id);
    }

    @PutMapping (value = "/update")
    public Product productUpdate(@RequestBody Product product) {
        return productService.update(product);
    }

    @GetMapping(value = "/all")
    public List<Product> productGet(){
        return productService.getAll();
    }


//    @GetMapping(value = "/get/all")
//    public ResponseEntity<?> productgetAll(@RequestParam(required = false) String name,
//                                                 @RequestParam(required = false) Long maxPrice,
//                                                 @RequestParam(required = false , defaultValue = "1") Integer page,
//                                                 @RequestParam(required = false, defaultValue = "5") Integer size) {
//
//        Page<ProductResponse> productResponses = productService.searchNameOrPrice(name, maxPrice, page, size);
//
//// Mendapatkan data produk sebagai daftar
//        List<ProductResponse> productData = productResponses.getContent();
//
//// Validasi jika data kosong
//        if (productData == null || productData.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(CommonResponse.<List<ProductResponse>>builder()
//                            .httpStatus(HttpStatus.OK)
//                            .statusCode(HttpStatus.OK.value())
//                            .message("No data found")
//                            .data(Collections.emptyList()) // Mengembalikan daftar kosong
//                            .pagingResponse(PagingResponse.builder()
//                                    .curentPage(page)
//                                    .totalPage(0)
//                                    .size(0)
//                                    .build())
//                            .build());
//        }
//
//// Menyusun metadata paging jika data ditemukan
//        PagingResponse pagingResponse = PagingResponse.builder()
//                .curentPage(productResponses.getNumber())
//                .totalPage(productResponses.getTotalPages())
//                .size(productResponses.getSize())
//                .build();
//
//// Mengembalikan respons jika data ditemukan
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(CommonResponse.<List<ProductResponse>>builder()
//                        .httpStatus(HttpStatus.OK)
//                        .statusCode(HttpStatus.OK.value())
//                        .message("Data Found")
//                        .data(productData) // Data produk
//                        .pagingResponse(pagingResponse) // Metadata paging
//                        .build());

//    }

    @DeleteMapping(value = "/delete/{id}")
    public void productDelete(@PathVariable String id) {
        productService.deleteById(id);
    }
}
