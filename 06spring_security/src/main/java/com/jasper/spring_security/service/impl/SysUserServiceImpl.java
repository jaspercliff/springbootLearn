package com.jasper.spring_security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.spring_security.domain.SysUser;
import com.jasper.spring_security.domain.dto.LoginParams;
import com.jasper.spring_security.mapper.SysUserMapper;
import com.jasper.spring_security.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
* @author 21903
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2024-01-30 14:24:19
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

    private final AuthenticationManager authenticationManager;
    @Override
    public String login(LoginParams loginParams) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginParams.getUsername(), loginParams.getPassword());
        Authentication authenticate = null;
        try {
            authenticate  =  authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
//            todo 业务异常 全局捕获
            log.error("用户名或密码错误 - {}", e.getMessage());
            return "用户名或密码错误";
        }
        SysUser sysUser = (SysUser)authenticate.getPrincipal();
        log.debug("登录的用户信息为 - {} ", sysUser);
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}




