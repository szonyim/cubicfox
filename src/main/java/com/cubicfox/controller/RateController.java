package com.cubicfox.controller;

import com.cubicfox.entity.Product;
import com.cubicfox.entity.Rate;
import com.cubicfox.entity.User;
import com.cubicfox.model.RateRequest;
import com.cubicfox.service.ProductService;
import com.cubicfox.service.RateService;
import com.cubicfox.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Set;


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

    @PostMapping("/{productId}")
    public ResponseEntity<?> rateProduct(@PathVariable("productId") long productId, @RequestBody() RateRequest rateRequest)
    {
        Product product = productService.getProduct(productId);

        if (product == null)
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);

        if(rateRequest.isValid() == false)
            return new ResponseEntity<>("Rate value must be between 1 and 10!", HttpStatus.BAD_REQUEST);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User user = userService.getUser(currentPrincipalName);
        Rate rate = new Rate(rateRequest.rateValue, user, product);

        try {
            rateService.addRate(rate);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(rate);
    }


    // Other version of handling validations
//    @ExceptionHandler({ TransactionSystemException.class })
//    public ResponseEntity<?> handleConstraintViolation(Exception ex, WebRequest request) {
//        ResponseEntity<?> re = new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
//        Throwable cause = ((TransactionSystemException) ex).getRootCause();
//        if (cause instanceof ConstraintViolationException) {
//            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
//            re = new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
//        }
//
//        return re;
//    }

}
