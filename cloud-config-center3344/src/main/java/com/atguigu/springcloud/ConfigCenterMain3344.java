package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @ProjectName springcloud
 * @Package com.atguigu.springcloud
 * @ClassName ConfigCenterMain
 * @Author ZCC
 * @Date 2022/06/19
 * @Description TODO
 * @Version 1.0
 */

@EnableConfigServer
@SpringBootApplication
public class ConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class);
    }
}
