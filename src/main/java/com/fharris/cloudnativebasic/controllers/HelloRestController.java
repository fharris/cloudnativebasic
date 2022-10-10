package com.fharris.cloudnativebasic.controllers;

import com.fharris.cloudnativebasic.entities.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    @GetMapping("/rest")
    public Greeting greeting(@RequestParam(required = false,
            defaultValue = "World") String name){
        return new Greeting(String.format("Hello, %s!", name));
    }
}