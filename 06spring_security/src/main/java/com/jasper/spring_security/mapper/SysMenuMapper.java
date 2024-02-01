package com.jasper.spring_security.mapper;

import com.jasper.spring_security.domain.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 21903
* @description 针对表【sys_menu(菜单表)】的数据库操作Mapper
* @createDate 2024-01-31 10:48:26
* @Entity com.jasper.spring_security.domain.SysMenu
*/
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

}




