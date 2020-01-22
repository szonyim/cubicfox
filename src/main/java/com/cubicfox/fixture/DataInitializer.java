package com.cubicfox.fixture;

import com.cubicfox.entity.User;
import com.cubicfox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if(userRepository.existsUserByUsername("admin") == false)
        {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("Secret123"));
            userRepository.saveAndFlush(user);

            // TODO: add some product and user ...
        }
    }
}
