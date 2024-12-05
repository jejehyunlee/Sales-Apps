package com.aegis.sales_apps.repository;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 9/25/2023 13:11
@Last Modified 9/25/2023 13:11
Version 1.0
*/


import com.aegis.sales_apps.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransactionDetaillRepository extends JpaRepository<TransactionDetail, String>, JpaSpecificationExecutor<TransactionDetail> {
}
