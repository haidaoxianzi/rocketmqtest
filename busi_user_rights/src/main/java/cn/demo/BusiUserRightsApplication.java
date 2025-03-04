package cn.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.demo.dao")
public class BusiUserRightsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusiUserRightsApplication.class, args);
    }

}
