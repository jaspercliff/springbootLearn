package com.jasper.spring_security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.spring_security.domain.SysUser;
import com.jasper.spring_security.domain.dto.LoginParams;
import com.jasper.spring_security.mapper.SysUserMapper;
import com.jasper.spring_security.service.SysUserService;
import com.jasper.spring_security.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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
    private final JWTUtils jwtUtils;
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
        if (sysUser == null){
            return "登录失败";
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id",sysUser.getId());
        hashMap.put("username",sysUser.getUsername());
        hashMap.put("avatar",sysUser.getAvatar());
        hashMap.put("perms",sysUser.getAuthorities());
        return jwtUtils.generateToken(hashMap);

    }
}




