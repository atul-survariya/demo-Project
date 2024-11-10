package com.scaler.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @RequestMapping("/hello/{name}")
    public String sayHello(@PathVariable("name")String personName){
        return "Hello Atul123 " + personName;
    }
    @RequestMapping("/hello")
    public String onlyHello(){
        return "only Hello Hi";
    }
}
