package com.cubicfox.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class WelcomeController {

    @GetMapping("/")
    public ResponseEntity<String> HelloSpring()
    {
        return ResponseEntity.ok("Hello Spring :)");
    }

}
