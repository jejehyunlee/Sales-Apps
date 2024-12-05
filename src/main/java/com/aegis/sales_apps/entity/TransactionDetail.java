package com.aegis.sales_apps.entity;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 9/25/2023 11:39
@Last Modified 9/25/2023 11:39
Version 1.0
*/

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "m_transaction_detail")
public class TransactionDetail {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_order_detail")
    private String idTrxDetail;

    @ManyToOne
    @JoinColumn(name = "trx_id")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "product_price_id")
    private ProductPrice productPrice;

    private Integer quantity;

}
