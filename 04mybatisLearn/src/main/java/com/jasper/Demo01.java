package com.jasper;

import com.jasper.domain.SysUser;
import com.jasper.mapper.SysUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Demo01 {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try (SqlSession sqlsession = sqlSessionFactory.openSession()){
//            mapper 映射器接口
            SysUserMapper mapper = sqlsession.getMapper(SysUserMapper.class);
            SysUser userById = mapper.getUserById(1);
            System.out.println("userById = " + userById);
            SysUser admin = mapper.getByUsernameSysUser("admin");
            System.out.println("admin = " + admin);
        }
    }
}
