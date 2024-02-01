package com.jasper.spring_security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.spring_security.domain.SysRole;
import com.jasper.spring_security.service.SysRoleService;
import com.jasper.spring_security.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 21903
* @description 针对表【sys_role(角色表)】的数据库操作Service实现
* @createDate 2024-01-31 10:48:26
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService{

}




