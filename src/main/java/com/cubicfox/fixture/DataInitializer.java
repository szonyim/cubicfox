package com.cubicfox.fixture;

import com.cubicfox.entity.Product;
import com.cubicfox.entity.Rate;
import com.cubicfox.entity.User;
import com.cubicfox.repository.ProductRepository;
import com.cubicfox.repository.RateRepository;
import com.cubicfox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    RateRepository rateRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        User user = null;
        if(userRepository.existsUserByUsername("admin") == false)
        {
            user = new User("admin", passwordEncoder.encode("Secret123"), "admin@cubicfox.hu");
            userRepository.saveAndFlush(user);
        }  else {
            user = userRepository.findByUsername("admin");
        }


        if(productRepository.count() == 0) {
            ArrayList<Product> demoProducts = new ArrayList<>();
            for (int i = 100000; i <= 100105; i++) {
                String name = "Product " + i;
                String desc = "Description " + i;
                Product p = new Product(i, name, desc, i, user);
                demoProducts.add(p);
            }
            productRepository.saveAll(demoProducts);
        }

        // Add test rates for a product
        if(rateRepository.count() == 0){
            Optional<Product> p = productRepository.findById((long)4);
            ArrayList<Rate> demoRatesForFirstProduct = new ArrayList<>();
            if(p.isPresent()){
                for (int i = 1; i <= 5; i++){
                    Rate r = new Rate((byte)i, user, p.get());
                    demoRatesForFirstProduct.add(r);
                }

                rateRepository.saveAll(demoRatesForFirstProduct);
            }

        }
    }
}
