package com.jasper.spring_security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.spring_security.domain.SysMenu;
import com.jasper.spring_security.service.SysMenuService;
import com.jasper.spring_security.mapper.SysMenuMapper;
import org.springframework.stereotype.Service;

/**
* @author 21903
* @description 针对表【sys_menu(菜单表)】的数据库操作Service实现
* @createDate 2024-01-31 10:48:26
*/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
    implements SysMenuService{

}




