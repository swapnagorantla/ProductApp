package com.hackerrank.eshopping.product.dashboard.controller;

import com.hackerrank.eshopping.product.dashboard.Response;
import com.hackerrank.eshopping.product.dashboard.model.Product;
import com.hackerrank.eshopping.product.dashboard.request.updateRequest;
import com.hackerrank.eshopping.product.dashboard.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Optional;

@RestController
@ComponentScan
@RequestMapping(value = "/products")
public class ProductsController {
    @Autowired
    ProductService productService;

    HttpHeaders headers = new HttpHeaders();

    @PostMapping(value = "", produces = "application/json" )
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product )  {

           if (productService.getProductById(product.getId()) != null) {
                return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
            }
           Product savedProduct = productService.saveProduct(product);
            return new ResponseEntity<Product>(savedProduct, new HttpHeaders(), HttpStatus.CREATED);

    }

    @GetMapping(value = "/{product_id}")
    public ResponseEntity<Product> getProduct(@Valid @PathVariable("product_id") Long id)  {

        Product entity =  productService.getProductById(id);
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        if(entity != null) {
            return new ResponseEntity<>(entity, headers, HttpStatus.OK);
        }else{
            return new ResponseEntity<>( new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{product_id}")
    public ResponseEntity<Product> updateProduct(@Valid @PathVariable("product_id") Long id, @Valid @RequestBody updateRequest product )  {

        Product entity =  productService.getProductById(id);

        if(entity != null) {
            Product updateProduct = new Product(entity.getId(), entity.getName(),entity.getCategory(),entity.getRetail_price(),entity.getDiscounted_price(),Boolean.parseBoolean(entity.getCategory()));
            updateProduct.setRetail_Price(product.getRetail_price());
            updateProduct.setDiscounted_Price(product.getDiscounted_price());
            updateProduct.setAvailability(product.getAvailability());
            productService.saveProduct(updateProduct);

            return new ResponseEntity<Product>(entity, new HttpHeaders(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>( new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }



   @GetMapping("")
    public ResponseEntity<List<Product>> getProductbyCategory(@RequestParam(name="category" , required = false) String category, @RequestParam(name="availability" , required = false, defaultValue = "-1") int availability) throws UnsupportedEncodingException {
        String cat = null;

        if(category != null) {
             cat = URLDecoder.decode(category, "UTF-8");
        }

       List<Product> productList;
        if(cat != null && availability == -1){

            productList =  productService.listProductsbyCatregory(cat);
        }else if(cat != null && availability != -1){

            productList =  productService.listProductsbyCatregoryAvailabilty(cat,availability);
        }else{

            productList = productService.listAllProducts();
        }

        return new ResponseEntity<>(productList, new HttpHeaders(), HttpStatus.OK);

    }
}
