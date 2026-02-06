package com.example.Ecom_project.controller;


import com.example.Ecom_project.model.Product;
import com.example.Ecom_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
//import java.util.Optional;

//@CrossOrigin
@RestController
@RequestMapping("/api")  // by default it is @GetMapping()
//@CrossOrigin(origins = "http://localhost:5173")
@CrossOrigin("*")
public class productController {

    @Autowired
    private ProductService service;

//    @GetMapping("/products")
//    public List<Product> getAllProducts(){
//
//        return service.getAllProducts();
//    }

    // ResponseEntity : it is a good practice to sent response with data
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

//    @GetMapping("/product/{id}")
//    public Optional<Product> getProducts(@PathVariable int id){
//
//        return service.getProductById(id);
//
////        return ResponseEntity<>(service.getProductById(id), HttpStatus.OK);
////        Optional<Product> prd = service.getProductById(id);
////
////        if(prd.isPresent())    // another method we can use map
////        return new ResponseEntity<>(prd, HttpStatus.OK);
////        else
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

@GetMapping("/product/{id}")
public ResponseEntity<Product> getProduct(@PathVariable int id) {
    Product product = service.getProduct(id);
    if (product != null) {
        return new ResponseEntity<>(product, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

    //@RequestBody accepts whole object or json as object
    //@RequestPart accept in different parts
    // ? because we are not sure either it is data or exception
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile){

        System.out.println("Hey product recieved !");

        try {
            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
//
//    @PostMapping("/product")
//    public void addProduct(@RequestBody Product product, @RequestBody MultipartFile imageFile){
//
//            service.addProduct(product, imageFile);
//    }

    // we are getting image in byte formate
    @GetMapping("product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId)
    {
        Product product = service.getProduct(productId);
        byte[] imageFile = product.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);


    }

}
