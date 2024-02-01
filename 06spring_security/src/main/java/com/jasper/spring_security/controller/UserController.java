package com.jasper.spring_security.controller;

import com.jasper.spring_security.domain.dto.LoginParams;
import com.jasper.spring_security.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final SysUserService service;

    @PostMapping("/login")
    public String login(@RequestBody LoginParams loginParams) {
        return service.login(loginParams);
    }

}
