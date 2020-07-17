package com.teng.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        
        return "Hello " + name + " , Welcome to Nacos Discovery!!!";
        
    }
}