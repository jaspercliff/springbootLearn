package com.jasper.spring_security;

import com.jasper.spring_security.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashMap;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils jwtUtils;
    @Test
    public void test(){
        String pass = "123456";
        String result = passwordEncoder.encode(pass);
        System.out.println("result = " + result);
//        front backend
        boolean matches = passwordEncoder.matches(pass, result);
        System.out.println("matches = " + matches);
    }

    @Test
    public void generateToken(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",1L);
        map.put("username","jasper");
        map.put("perms", Arrays.asList("p1","p2","p3"));
        String token = JWTUtils.generateToken(map);
        System.out.println("token = " + token);
    }

    @Test
    public void parseToken(){
        Claims claims = JWTUtils.parseToken(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwZXJtcyI6W3siYXV0aG9yaXR5Ijoic3lzdGVtOnVzZXI6bGlzdCJ9LHsiYXV0aG9yaXR5Ijoic3lzdGVtOm1lbnU6bGlzdCJ9LHsiYXV0aG9yaXR5Ijoic3lzdGVtOnJvbGU6bGlzdCJ9XSwiaWQiOiIxIiwidXNlcm5hbWUiOiJhZG1pbiIsImlhdCI6MTcwNjc3MTc2MiwiZXhwIjoxNzA2NzczNTYyfQ.KzTB7S7_E3ObjrZ6um_dwQDcoT2du8UfogN2PK5jf60"
        );
        System.out.println("claims = " + claims);
    }
}
