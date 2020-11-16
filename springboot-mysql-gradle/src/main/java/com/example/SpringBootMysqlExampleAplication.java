package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author zhangy
 * @Time 2020/4/4 23:11
 */
@SpringBootApplication
@MapperScan("com.example.mapper")
public class SpringBootMysqlExampleAplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMysqlExampleAplication.class,args);
    }
}
