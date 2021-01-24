package com.hackerrank.eshopping.product.dashboard.services;

import com.hackerrank.eshopping.product.dashboard.dao.ProductRepository;
import com.hackerrank.eshopping.product.dashboard.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> listAllProducts() {

        List<Product>  sortList = (List<Product>) productRepository.findAll();

        sortList.sort(Comparator.comparing(Product::getId));
        return sortList;
    }

    @Override
    public List<Product> listProductsbyCatregory(String category) {

        List<Product>  sortList = productRepository.findProductsByCategory(category);

        sortList.sort(Comparator.comparing(Product::getDiscounted_price)
                .thenComparing(Product::getId)
                .thenComparing(Product::getAvailability));

        return sortList;
    }

    @Override
    public List<Product> listProductsbyCatregoryAvailabilty(String category, int avail) {
        boolean availabilty;
        if(avail == 0){
            availabilty = false;
        }else{
            availabilty = true;
        }


        List<Product>  sortList = productRepository.findProductsByCategoryAndAvailability(category,availabilty);

       Collections.sort(sortList, new Comparator<Product>() {
            @Override
            public int compare(Product o2, Product o1) {
                int discountPercentage1 = o1.getDiscountedPercentage(o1.getRetail_price(),o1.getDiscounted_price());
                int discountPercentage2 = o2.getDiscountedPercentage(o2.getRetail_price(),o2.getDiscounted_price());
                int val= (int) (discountPercentage1-discountPercentage2);

                return val;
            }
        });

        sortList.sort(Comparator.comparing(Product::getAvailability));



        return sortList;
    }



    @Override
    public Product getProductById(Long id) {
        return productRepository.findProductsById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }


}
