package com.tra24;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping("h")
    public String hello(){
        return "Hello there";
    }
}

