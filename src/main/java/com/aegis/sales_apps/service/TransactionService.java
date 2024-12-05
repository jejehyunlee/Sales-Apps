package com.aegis.sales_apps.service;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 9/25/2023 13:13
@Last Modified 9/25/2023 13:13
Version 1.0
*/


import com.aegis.sales_apps.model.request.TransactionReportRequest;
import com.aegis.sales_apps.model.request.TransactionRequest;
import com.aegis.sales_apps.model.response.TransactionResponse;

import java.util.List;

public interface TransactionService {

    TransactionResponse createNewTransaction(TransactionRequest transactionRequest);
    TransactionResponse getOrderById(String id);
    List<TransactionReportRequest> getReport();

    /*
    * Update -> Optional (Tergantung Bisnis)
    * Delete -> Optional (Tergantung Bisnis)
    * */

}
