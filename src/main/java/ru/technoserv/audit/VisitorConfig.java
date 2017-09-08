package ru.technoserv.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class VisitorConfig {

    @Bean
    public Visitor visitor(){
        return new Visitor();
    }
}