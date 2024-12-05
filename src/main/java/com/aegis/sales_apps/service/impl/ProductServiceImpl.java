package com.aegis.sales_apps.service.impl;

/*
Created By IntelliJ IDEA 2022.1.3 (Community Edition)
Build #IC-221.5921.22, built on June 21, 2022
@Author JEJE a.k.a Jefri S
Java Developer
Created On 9/21/2023 14:40
@Last Modified 9/21/2023 14:40
Version 1.0
*/


import com.aegis.sales_apps.entity.Product;
import com.aegis.sales_apps.entity.ProductPrice;
import com.aegis.sales_apps.model.request.ProductReportRequest;
import com.aegis.sales_apps.model.request.ProductRequest;
import com.aegis.sales_apps.model.response.ProductResponse;
import com.aegis.sales_apps.repository.ProductRepository;
import com.aegis.sales_apps.service.ProductPriceService;
import com.aegis.sales_apps.service.ProductService;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductPriceService productPriceService;


    @Override
    public List<ProductReportRequest> getProductReportData() {
        return productRepository.findAll().stream().map(product -> {
            // Map ProductPrice menjadi PriceDetail
            List<ProductReportRequest.ProductPrice> priceDetails = product.getProductPrice().stream()
                    .map(price -> new ProductReportRequest.ProductPrice(price.getPrice(), price.getStock()))
                    .collect(Collectors.toList());

            // Buat ProductReportDTO untuk setiap produk
            return new ProductReportRequest(
                    product.getName(),
                    product.getDesc(),
                    priceDetails
            );
        }).collect(Collectors.toList());
    }


    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(String id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Product product) {
        Product product1 = getById(product.getIdProduct());
        if(product1 != null){
            productRepository.save(product);
        }
        return null;
    }

    @Override
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }


    @Transactional(rollbackOn = Exception.class)
    @Override
    public ProductResponse createProduct(ProductRequest request) {


        Product product = Product.builder()
                .name(request.getProductName())
                .desc(request.getProductDesc())
                .build();
        productRepository.saveAndFlush(product);

        ProductPrice productPrice = ProductPrice.builder()
                .price(request.getPrice())
                .stock(request.getStock())
                .product(product)
                .isActive(true)
                .build();
        productPriceService.create(productPrice);

        return toProductResponse(product, productPrice);

    }

    private ProductResponse toProductResponse(Product product, ProductPrice productPrice) {
        return ProductResponse.builder()
                .productId(product.getIdProduct())
                .productName(product.getName())
                .productDesc(product.getDesc())
                .price(productPrice.getPrice())
                .stock(productPrice.getStock())
                .build();
    }

    @Override
    public Page<ProductResponse> searchNameOrPrice(String name, Long maxPrice, Integer page, Integer size) {
        Specification<Product> specification = ((root, query, criteriaBuilder) ->{

            Join<Product, ProductPrice> productPrices = root.join("productPrice");
                    List<Predicate> predicates = new ArrayList<>();
                    if (name != null){
                         predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name"))
                        ,"%" + name.toLowerCase() + "%"));
                    }
                    if (maxPrice != null){
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(productPrices.get("price"),maxPrice));
                    }
            return query.where(predicates.toArray(new jakarta.persistence.criteria.Predicate[]{})).getRestriction();
        });

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(specification, pageable);
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product: products.getContent()) {
            Optional<ProductPrice> productPrice = product.getProductPrice().stream().filter(ProductPrice::getIsActive).findFirst();
        if (productPrice.isEmpty())continue;

        productResponses.add(toProductResponse(product, productPrice.get()));
        }
        return new PageImpl<>(productResponses, pageable, products.getTotalElements());
    }
}
