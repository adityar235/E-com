package com.example.Ecom_project.service;

import com.example.Ecom_project.model.Product;
import com.example.Ecom_project.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {

        return repo.findAll();
    }

//    // Optional<Product> means product may exist or may not exist
//    public Optional<Product> getProductById(int id) {
//        return repo.findById(id);   // this will return us optional product(null or product)
//        // method use function as product but return repo.findById(id).orElse(new Product());   empty product else
//
//    }

    public Product getProduct(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {

        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        return repo.save(product);

    }
}
