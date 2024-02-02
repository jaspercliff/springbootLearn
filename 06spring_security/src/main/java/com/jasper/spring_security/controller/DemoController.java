package com.jasper.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("test")
//    @PreAuthorize("hasAuthority('system:user:list')")
    public String getTest() {
        return "spring security test";
    }


    @GetMapping("test1")
//    @PreAuthorize("hasAuthority('system:role:list')")
    public String test1() {
        return "spring security test1";
    }

    //    动态权限
    @GetMapping("system/user/list")
    public String userList() {
        return "system/user/list";
    }
    @GetMapping("system/role/list")
    public String roleList() {
        return "system/role/list";
    }

}
