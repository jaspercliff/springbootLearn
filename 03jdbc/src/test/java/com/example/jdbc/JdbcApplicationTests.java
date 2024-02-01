package com.example.jdbc;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class JdbcApplicationTests {

    @Resource
    private JdbcTemplate jdbcTemplate;
    @Test
    void contextLoads() {
    }

    @Test
    void updateUser(){
        String sql = "insert into user(id,name) values(1,'jasper')";
        jdbcTemplate.update(sql);
    }

}
