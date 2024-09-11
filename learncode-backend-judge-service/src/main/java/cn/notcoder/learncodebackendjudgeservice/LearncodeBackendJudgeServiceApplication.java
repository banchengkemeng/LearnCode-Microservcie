package cn.notcoder.learncodebackendjudgeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"cn.notcoder.learncodebackendserviceclient.service"})
@ComponentScan("site.notcoder")
@ComponentScan("cn.notcoder")
public class LearncodeBackendJudgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearncodeBackendJudgeServiceApplication.class, args);
    }
}
