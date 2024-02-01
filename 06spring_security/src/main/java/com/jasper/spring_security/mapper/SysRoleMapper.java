package com.jasper.spring_security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasper.spring_security.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 21903
* @description 针对表【sys_role(角色表)】的数据库操作Mapper
* @createDate 2024-01-31 10:48:26
* @Entity com.jasper.spring_security.domain.SysRole
*/
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {


    String getPerms(String roleIdArray);
}




