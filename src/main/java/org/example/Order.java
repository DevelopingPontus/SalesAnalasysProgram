package org.example;

import java.util.List;

public class Order {
    int orderId;
    List<Product> products;
    String customerName;

    public int getOrderId() {
        return orderId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Order(int orderId, List<Product> products, String customerName) {
        this.orderId = orderId;
        this.products = products;
        this.customerName = customerName;
    }
}
