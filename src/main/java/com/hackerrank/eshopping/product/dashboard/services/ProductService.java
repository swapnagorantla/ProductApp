package com.hackerrank.eshopping.product.dashboard.services;

import com.hackerrank.eshopping.product.dashboard.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> listAllProducts();

    List<Product> listProductsbyCatregory(String Category);

    List<Product> listProductsbyCatregoryAvailabilty(String Category,int Availability);

    Product getProductById(Long id);

    Product saveProduct(Product product);

}
