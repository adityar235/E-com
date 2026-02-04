package com.example.Ecom_project.controller;

import com.example.Ecom_project.model.Product;
import com.example.Ecom_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin
@RestController
@RequestMapping("/api")  // by default it is @GetMapping()
@CrossOrigin(origins = "http://localhost:5173")
public class productController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

//    // ResponseEntity : it is a good practice to sent response with data
//    @GetMapping("/products")
//    public ResponseEntity<List<Product>> getAllProducts(){
//        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.FOUND);
//    }

    @GetMapping("/product/{id}")
    public Optional<Product> getProducts(@PathVariable int id){

        return service.getProductById(id);

//        return ResponseEntity<>(service.getProductById(id), HttpStatus.OK);
//        Optional<Product> prd = service.getProductById(id);
//
//        if(prd.isPresent())    // another method we can use map
//        return new ResponseEntity<>(prd, HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
