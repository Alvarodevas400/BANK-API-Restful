package com.alvaro.bankapi.infrastructure.config;

import com.ibm.as400.access.AS400ConnectionPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:as400Connection.properties")
public class AS400Config {

    @Bean
    public AS400ConnectionPool as400ConnectionPool(){
        AS400ConnectionPool connectionPool = new AS400ConnectionPool();
        connectionPool.setMaxConnections(10);
        return connectionPool;
    }
}
