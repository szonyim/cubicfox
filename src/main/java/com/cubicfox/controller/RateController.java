package com.cubicfox.controller;

import com.cubicfox.entity.Product;
import com.cubicfox.entity.Rate;
import com.cubicfox.entity.User;
import com.cubicfox.exception.RateException;
import com.cubicfox.exception.ResourceNotFoundException;
import com.cubicfox.model.RateRequest;
import com.cubicfox.repository.ProductRepository;
import com.cubicfox.service.ProductService;
import com.cubicfox.service.RateService;
import com.cubicfox.service.UserService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("/rate")
public class RateController {

    @Autowired
    private ProductService productService;

    @Autowired
    private RateService rateService;

    @Autowired
    private UserService userService;

    @PostMapping("/{productId}/{rateValue}")
    public ResponseEntity<?> rateProduct(@PathVariable("productId") long productId, @PathVariable("rateValue") byte rateValue) throws ResourceNotFoundException {

        Product product = productService.getProduct(productId);

        if(product == null)
            throw new ResourceNotFoundException("Resource not found");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User user = userService.getUser(currentPrincipalName);

        Rate rate = new Rate(rateValue, user, product);


        rateService.addRate(rate);


        return ResponseEntity.ok(rate);
    }

    @ExceptionHandler
    public void handle(ConstraintViolationException exception) {
        //you will get all javax failed validation, can be more than one
        //so you can return the set of error messages or just the first message
        int a = 0;
    }

}
