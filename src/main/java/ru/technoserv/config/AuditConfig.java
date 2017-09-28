package ru.technoserv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.technoserv.audit.AuditHandler;

//@Configuration
//@EnableAspectJAutoProxy
public class AuditConfig {

   // @Bean
    public AuditHandler auditHandler(){
        return new AuditHandler();
    }

}
