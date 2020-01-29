package com.cubicfox.controller;

import com.cubicfox.entity.Product;
import com.cubicfox.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductRepository productRepository;
    private static int PAGE_SIZE = 20;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value = "", name = "get-products")
    public ResponseEntity all(@RequestParam(name = "page",  required = false) Integer page, @RequestParam(name="filter", required = false) String filter){

        if(page == null)
            page = 0;

        Pageable pageable = PageRequest.of(page, PAGE_SIZE);

        Page<Product> productList = null;
        if(filter != null && !"".equals(filter)) {
            productList = productRepository.findAllByNameContainsOrDescriptionContains(filter, filter, pageable);
        }else{
            productList = productRepository.findAll(pageable);
        }

        return ResponseEntity.ok(productList);
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
