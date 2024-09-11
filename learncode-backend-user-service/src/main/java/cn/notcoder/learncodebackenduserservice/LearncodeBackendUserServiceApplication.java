package cn.notcoder.learncodebackenduserservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("cn.notcoder.learncodebackenduserservice.mapper")
@ComponentScan("cn.notcoder")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"cn.notcoder.learncodebackendserviceclient.service"})
public class LearncodeBackendUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearncodeBackendUserServiceApplication.class, args);
    }
}
