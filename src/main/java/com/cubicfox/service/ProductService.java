package com.cubicfox.service;

import com.cubicfox.entity.Product;
import com.cubicfox.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product getProduct(long id){
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent())
            return product.get();

        return null;
    }

}
