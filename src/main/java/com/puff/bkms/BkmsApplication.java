package com.puff.bkms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.puff.bkms.mapper")
public class BkmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BkmsApplication.class, args);
    }

}
