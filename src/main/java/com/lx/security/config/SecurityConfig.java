package com.lx.security.config;

import com.lx.security.constant.SecurityConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "security.gateway")
public class SecurityConfig {
    /*
    * 是否只能通过网关请求服务器
    * */
    private Boolean onlyGateway = Boolean.TRUE;

    /*
    * 认证字段
    * */
    private String authKey = SecurityConstant.AUTH_KEY;

    /*
    * 认证值
    * */
    private String authValue = SecurityConstant.AUTH_VALUE;
}
