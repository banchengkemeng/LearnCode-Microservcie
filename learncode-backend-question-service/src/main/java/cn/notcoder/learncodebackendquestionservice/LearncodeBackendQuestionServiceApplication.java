package cn.notcoder.learncodebackendquestionservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@MapperScan("cn.notcoder.learncodebackendquestionservice.mapper")
@ComponentScan("cn.notcoder")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"cn.notcoder.learncodebackendserviceclient.service"})
public class LearncodeBackendQuestionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearncodeBackendQuestionServiceApplication.class, args);
    }
}
