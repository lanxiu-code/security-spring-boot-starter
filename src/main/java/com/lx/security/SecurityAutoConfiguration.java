package com.lx.security;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.lx.security")
@ConditionalOnProperty(prefix = "security.gateway", name = "only-gateway", havingValue = "true")
@EnableConfigurationProperties
public class SecurityAutoConfiguration {

}
