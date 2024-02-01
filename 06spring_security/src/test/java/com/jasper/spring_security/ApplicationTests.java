package com.jasper.spring_security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test(){
        String pass = "123456";
        String result = passwordEncoder.encode(pass);
        System.out.println("result = " + result);
//        front backend
        boolean matches = passwordEncoder.matches(pass, result);
        System.out.println("matches = " + matches);
    }
}
