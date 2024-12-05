package com.aegis.sales_apps.service.impl;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 9/25/2023 13:18
@Last Modified 9/25/2023 13:18
Version 1.0
*/


import com.aegis.sales_apps.entity.User;
import com.aegis.sales_apps.entity.Transaction;
import com.aegis.sales_apps.entity.TransactionDetail;
import com.aegis.sales_apps.entity.ProductPrice;
import com.aegis.sales_apps.model.request.TransactionReportRequest;
import com.aegis.sales_apps.model.request.TransactionRequest;
import com.aegis.sales_apps.model.response.*;
import com.aegis.sales_apps.repository.TransactionDetaillRepository;
import com.aegis.sales_apps.repository.TransactionRepository;
import com.aegis.sales_apps.service.UserService;
import com.aegis.sales_apps.service.TransactionService;
import com.aegis.sales_apps.service.ProductPriceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final UserService userService;
    private final ProductPriceService productPriceService;
    private final TransactionRepository transactionRepository;

    private final TransactionDetaillRepository transactionDetaillRepository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public TransactionResponse createNewTransaction(TransactionRequest transactionRequest) {
        /* TODO 1: Get logged-in user */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }

        Object principal = authentication.getPrincipal();
        String email = (principal instanceof UserDetails)
                ? ((UserDetails) principal).getUsername()
                : principal.toString();

        // Mendapatkan user dari email yang login
        User user = userService.getByEmail(email);

        /* TODO 2: Convert order detail request to TransactionDetail */
        List<TransactionDetail> transactionDetails = transactionRequest.getTransactionDetailRequests().stream().map(orderDetailRequest -> {
            // TODO 3: Validate Product Price
            ProductPrice productPrice = productPriceService.getByID(orderDetailRequest.getProductPriceId());
            return TransactionDetail.builder()
                    .productPrice(productPrice)
                    .quantity(orderDetailRequest.getQuantity())
                    .build();
        }).collect(Collectors.toList());

        /* TODO 4: Create New Transaction */
        Transaction transaction = Transaction.builder()
                .user(user)
                .transDate(LocalDateTime.now())
                .transactionDetails(transactionDetails)
                .build();
        transactionRepository.saveAndFlush(transaction);

        transaction.getTransactionDetails().forEach(transactionDetail -> {
            transactionDetail.setTransaction(transaction);
            transactionDetaillRepository.save(transactionDetail);
        });

        /* TODO 5: Update the stock from purchase quantity */
        List<TransactionDetailResponse> transactionDetailResponses = transaction.getTransactionDetails().stream().map(transactionDetail -> {
            transactionDetail.setTransaction(transaction);
            ProductPrice currentProductPrice = transactionDetail.getProductPrice();
            currentProductPrice.setStock(currentProductPrice.getStock() - transactionDetail.getQuantity());
            return TransactionDetailResponse.builder()
                    .orderDetailId(transactionDetail.getIdTrxDetail())
                    .quantity(transactionDetail.getQuantity())
                    /* TODO 6: Convert product to ProductResponse */
                    .product(ProductResponse.builder()
                            .productId(currentProductPrice.getProduct().getIdProduct())
                            .productName(currentProductPrice.getProduct().getName())
                            .productDesc(currentProductPrice.getProduct().getDesc())
                            .price(currentProductPrice.getPrice())
                            .stock(currentProductPrice.getStock())
                            .build())
                    .build();
        }).collect(Collectors.toList());

        /* TODO 8: Convert user to UserResponse */
        UserResponse userResponse = UserResponse.builder()
                .idUser(user.getIdUser())
                .name(user.getName())
                .build();

        /* TODO 9: Build and return TransactionResponse */
        return TransactionResponse.builder()
                .orderId(transaction.getIdOrder())
                .user(userResponse)
                .transDate(transaction.getTransDate())
                .transactionDetailRespons(transactionDetailResponses)
                .build();
    }

    @Override
    public TransactionResponse getOrderById(String id) {
        return null;
    }

    @Override
    public List<TransactionReportRequest> getReport() {

        return transactionRepository.findAll().stream()
                .flatMap(transaction ->
                        transaction.getTransactionDetails().stream().map(detail -> {
                            // Get the user's name
                            String userName = transaction.getUser().getName();
                            // Get the product's name from the product associated with the productPrice
                            String productName = detail.getProductPrice().getProduct().getName();
                            // Get the quantity of the product in the transaction detail
                            int quantity = detail.getQuantity();

                            // Return a new TransactionReportRequest for each transaction detail
                            return new TransactionReportRequest(
                                    transaction.getTransDate(), // Date of transaction
                                    userName,                   // User's name
                                    productName,                // Product's name
                                    quantity                    // Quantity purchased
                            );
                        })
                ).collect(Collectors.toList());  // Collect all the transaction details into a list

    }

}
