package com.aegis.sales_apps.model.request;

import com.aegis.sales_apps.entity.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReportRequest {

    private String productName;
    private String productDescription;
    private List<ProductPrice> prices;

    public static class ProductPrice {
        private double price;
        private int stock;


        public ProductPrice(double price, int stock) {
            this.price = price;
            this.stock = stock;
        }

        public double getPrice() {
            return price;
        }

        public int getStock() {
            return stock;
        }
    }

}
