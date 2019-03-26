package com.uhsnarp.designpattern;

import com.uhsnarp.designpattern.prototype.ProtoFalse;
import com.uhsnarp.designpattern.prototype.ProtoTrue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DesignpatternApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesignpatternApplication.class, args);
    }

    @Bean
    public ProtoFalse protoFalse() {
        return new ProtoFalse();
    }

    @Bean
    @Scope("prototype")
    public ProtoTrue protoTrue() {
        return new ProtoTrue();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
