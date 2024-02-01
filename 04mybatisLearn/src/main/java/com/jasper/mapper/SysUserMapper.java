package com.jasper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasper.domain.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author 21903
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2024-01-31 16:51:22
* @Entity generator.domain.SysUser
*/
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * based on xml
     * @param username username
     * @return sysUser
     */
    public SysUser getByUsernameSysUser(@Param("username") String username);

    /**
     * based on annotation 不适合过于复杂的sql
     * @param id id
     * @return SysUser
     */
    @Select("select * from sys_user where ID = #{id}")
    public SysUser getUserById(Integer id);


}




