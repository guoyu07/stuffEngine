package ru.technoserv.Repository;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConf {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("employees");
    }

}
