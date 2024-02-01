package com.jasper.spring_security.domain.dto;

import lombok.Data;

/**
 * 登录 参数
 */
@Data
public class LoginParams {
    private String username;
    private String password;
}
