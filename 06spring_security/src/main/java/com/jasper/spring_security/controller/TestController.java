package com.jasper.spring_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {
    @GetMapping("to_login")
    public String hello() {
        return "login";
    }

    @GetMapping("index")
    public String index() {
        return "index";
    }
}
