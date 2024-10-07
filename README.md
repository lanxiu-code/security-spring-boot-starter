基于Spring Cloud Gateway 的项目，用于防止用户绕过网关直接访问服务器，只需在需要校验的项目中引入依赖，配置好yml即可生效。



## 第一步，引入依赖
```xml
<dependency>
    <groupId>io.github.lanxiu-code</groupId>
    <artifactId>security-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```



## 第二步，配置yml
```yaml
security:
  gateway:
    only-gateway: true
    auth-key: auth
    auth-value: lanxiu
```





## 配置项说明
| 配置 | 说明 |
| :---: | :---: |
|  only-gateway | 只能通过网关访问服务器 |
| auth-key | 网关中默认过滤器中AddRequestHeader的key |
| auth-value | 网关中默认过滤器中AddRequestHeader的value |




## 示例
### 项目目录
![](https://cdn.nlark.com/yuque/0/2024/png/35349136/1728266354628-01998f7b-17e5-4783-afbe-3d660f1ba605.png)

### 网关配置
> carsaleservice-gateway
>

```yaml
server:
  port: 8103
spring:
  main:
    web-application-type: reactive
  application:
    name: gateway-service
  cloud:
    nacos:
      username: nacos
      password: nacos
      discovery:
        server-addr: 127.0.0.1:8848
        namespace: 6212c2e1-8a52-4780-a3d3-55012b420acb
        group: car-sale-service
    gateway:
      routes:
        - id: api-service
          uri: lb://api-service
          predicates:
            - Path=/api/car/**
        - id: user-service
          uri: lb://user-service
          predicates:
            - Path=/api/user/**
      default-filters:
        - AddRequestHeader=auth,lanxiu

```



### 服务器项目配置
> carsaleservice-api-service
>

```yaml
# 测试配置文件
# 蓝朽
# 
server:
  port: 8102
spring:
  # 数据库配置
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/car_sale
    username: root
    password: root
  redis:
    database: 1
    host: 127.0.0.1
    port: 6379
    timeout: 5000
  cloud:
    nacos:
      username: nacos
      password: nacos
      discovery:
        server-addr: 127.0.0.1:8848
        namespace: 6212c2e1-8a52-4780-a3d3-55012b420acb
        group: car-sale-service
security:
  gateway:
    only-gateway: true
    auth-key: auth
    auth-value: lanxiu

```



### 访问


网关访问，显示正常

![](https://cdn.nlark.com/yuque/0/2024/png/35349136/1728266592887-e6dec58c-3b28-481c-a119-0e3638aa7686.png)



服务器直接访问，显示403

![](https://cdn.nlark.com/yuque/0/2024/png/35349136/1728266636525-668a2142-a909-457d-9e01-3d786d407405.png)

