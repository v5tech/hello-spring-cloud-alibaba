# hello-spring-cloud-alibaba-nacos-provider

### Nacos服务注册中心

http://localhost:8081/echo/hi

服务端点检查

http://localhost:8081/actuator/nacos-discovery

Nacos管理中心

http://localhost:8848/nacos

### Nacos作为配置中心

Spring Boot 配置文件的加载顺序，依次为 bootstrap.properties -> bootstrap.yml -> application.properties -> application.yml ，其中 bootstrap.properties 配置为最高优先级

可以使用 `spring.cloud.nacos.config.refresh.enabled=false` 来关闭动态刷新。

多环境配置

application-dev.yml、application-prod.yml， 启动项目时只需要增加一个命令参数 --spring.profiles.active=环境配置 即可

spring-cloud-starter-alibaba-nacos-config 在加载配置的时候，不仅仅加载了以 dataid 为 ${spring.application.name}.${file-extension:properties} 为前缀的基础配置，还加载了 dataid 为 ${spring.application.name}-${profile}.${file-extension:properties} 的基础配置。在日常开发中如果遇到多套环境下的不同配置，可以通过 Spring 提供的 ${spring.profiles.active} 这个配置项来配置。

在项目中增加一个名为 bootstrap-prod.properties 的配置文件，内容如下：

```
spring.profiles.active=prod
spring.application.name=nacos-provider-config
spring.cloud.nacos.config.file-extension=yaml
spring.cloud.nacos.config.server-addr=127.0.0.1:8848
```
