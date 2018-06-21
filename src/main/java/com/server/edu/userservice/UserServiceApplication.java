package com.server.edu.userservice;

import org.apache.servicecomb.springboot.starter.provider.EnableServiceComb;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.server.edu.userservice.dao")
@EnableServiceComb
public class UserServiceApplication
{
    
    public static void main(String[] args)
    {
        SpringApplication.run(UserServiceApplication.class, args);
    }
    
}
