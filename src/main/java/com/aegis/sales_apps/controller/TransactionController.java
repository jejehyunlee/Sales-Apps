package com.aegis.sales_apps.controller;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 9/25/2023 15:24
@Last Modified 9/25/2023 15:24
Version 1.0
*/

import com.aegis.sales_apps.model.request.TransactionRequest;
import com.aegis.sales_apps.model.response.CommonResponse;
import com.aegis.sales_apps.model.response.TransactionResponse;
import com.aegis.sales_apps.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/trx")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping( path = "/add")
    public ResponseEntity<?> create(@RequestBody TransactionRequest request) {
        TransactionResponse transactionResponse = transactionService.createNewTransaction(request);
        return ResponseEntity.status(HttpStatus.OK.value()).body(
        CommonResponse
                .<TransactionResponse>builder()
                .httpStatus(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .message("Success Create")
                .data(transactionResponse)
                .build());
    }
}
