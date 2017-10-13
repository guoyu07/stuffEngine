package ru.technoserv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.technoserv.audit.AuditHandler;
import ru.technoserv.services.AuditService;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("ru.technoserv.services")
public class AuditConfig {

    @Bean
    public AuditHandler auditHandler(AuditService service){
        return new AuditHandler(service);
    }

}
