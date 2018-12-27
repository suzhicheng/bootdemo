package com.bootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ServletComponentScan
@SpringBootApplication
@EnableCaching
public class BootDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootDemoApplication.class, args);
        System.out.println("ヾ(◍°∇°◍)ﾉﾞ    项目启动成功      ヾ(◍°∇°◍)ﾉﾞ\n");
    }
}
