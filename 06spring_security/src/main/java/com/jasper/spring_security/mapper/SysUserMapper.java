package com.jasper.spring_security.mapper;

import com.jasper.spring_security.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 21903
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2024-01-30 14:24:19
* @Entity com.jasper.spring_security.domain.SysUser
*/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser getUserByUsername(@Param("username") String username);
}




