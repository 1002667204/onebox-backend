package com.turing.onebox;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScans(@MapperScan({"com.turing.onebox.admin.mapper", "com.turing.onebox.home.mapper"}))
public class OneboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(OneboxApplication.class, args);
    }

}
