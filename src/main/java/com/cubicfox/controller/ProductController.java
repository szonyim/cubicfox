package com.cubicfox.controller;

import com.cubicfox.entity.Product;
import com.cubicfox.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/", name = "get-products")
    public ResponseEntity all(){
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping(value = "/{productId}", name = "get-product")
    public ResponseEntity<Product> getProduct(@PathVariable("productId") long id){
        Optional<Product> queryResult = productRepository.findById(id);

        Product product = null;

        if(queryResult.isPresent() == false){
            ResponseEntity.notFound();
        }
        else{
            product = queryResult.get();
        }

        return ResponseEntity.ok(product);
    }

    @PutMapping(value = "/{productId}", name = "update-product")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") long id, @RequestBody Product product){
        return null;
    }

}
