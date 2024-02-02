package com.jasper.spring_security.mapper;

import com.jasper.spring_security.domain.SysPersistLogin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;


/**
* @author 21903
* @description 针对表【sys_persist_login】的数据库操作Mapper
* @createDate 2024-02-02 11:23:07
* @Entity com.jasper.spring_security.domain.SysPersistLogin
*/
@Mapper
public interface SysPersistLoginMapper extends BaseMapper<SysPersistLogin> {

    @Delete("delete from sys_persist_login where username = #{username}")
    void deleteByUsername(String username);
}




