# 入门
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://49.234.34.109:3307/learn?useUnicode=true&amp;characterEncoding=utf-8&amp;
                useSSL=false&amp;serverTimezone=GMT"/>
                <property name="username" value="root"/>
                <property name="password" value="passwd"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="com/jasper/mapper/SysUserMapper.xml"/>
    </mappers>
</configuration>
```
mybatis的config

## mappers

在springboot中配置
@MapperScan("com.jasper.mapper")
会自动配置

## sqlSession
sqlSession 线程不安全的 不能共享

# configuration

