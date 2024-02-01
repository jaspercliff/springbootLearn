package com.jasper.spring_security.service;

import com.jasper.spring_security.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jasper.spring_security.domain.dto.LoginParams;

/**
* @author 21903
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2024-01-30 14:24:19
*/
public interface SysUserService extends IService<SysUser> {

    String login(LoginParams loginParams);

}
