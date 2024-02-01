package com.jasper.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("test")
    public String getTest(){
        return "spring security test";
    }


//    @PreAuthorize("hasAuthority('test:show')")
    @GetMapping("test1")
    public String test1(){
        return "spring security test1";
    }
}
