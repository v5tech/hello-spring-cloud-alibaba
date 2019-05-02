# hello-spring-cloud-external-skywalking

Spring Cloud Alibaba SkyWalking 分布式链路追踪

### SkyWalking 服务端配置

1、基于 Docker 安装 ElasticSearch

docker-compose.yml

```yaml
version: '3.3'
services:
  elasticsearch:
    image: wutang/elasticsearch-shanghai-zone:6.3.2
    container_name: elasticsearch
    restart: always
    ports:
      - 9200:9200
      - 9300:9300
    environment:
      cluster.name: elasticsearch
```

其中，9200 端口号为 SkyWalking 配置 ElasticSearch 所需端口号，cluster.name 为 SkyWalking 配置 ElasticSearch 集群的名称

浏览器访问 http://elasticsearchIP:9200/

2、下载并启动 SkyWalking

https://www.apache.org/dyn/closer.cgi/incubator/skywalking/6.0.0-GA/apache-skywalking-apm-incubating-6.0.0-GA.zip

https://www.apache.org/dyn/closer.cgi/incubator/skywalking/6.0.0-GA/apache-skywalking-apm-incubating-6.0.0-GA.tar.gz

3、配置 SkyWalking

下载完成后解压缩，进入 apache-skywalking-apm-incubating/config 目录并修改 application.yml 配置文件

主要做三件事:

1)注释 H2 存储方案

2)启用 ElasticSearch 存储方案

3)修改 ElasticSearch 服务器地址

4、启动 SkyWalking

修改完配置后，进入 apache-skywalking-apm-incubating\bin 目录，运行 startup.bat 启动服务端

浏览器访问 http://localhost:8080

默认的用户名密码为：admin/admin


### SkyWalking 客户端配置

探针文件在 apache-skywalking-apm-incubating/agent 目录下

Java Agent 服务器探针三种部署方式

* IDEA 部署探针

修改项目的 VM 运行参数，点击菜单栏中的 Run -> EditConfigurations...，此处我们以 nacos-provider 项目为例，修改参数如下

```
-javaagent:agent\skywalking-agent.jar
-Dskywalking.agent.service_name=nacos-provider
-Dskywalking.collector.backend_service=localhost:11800
```

-javaagent：用于指定探针路径

-Dskywalking.agent.service_name：用于重写 agent/config/agent.config 配置文件中的服务名

-Dskywalking.collector.backend_service：用于重写 agent/config/agent.config 配置文件中的服务地址


* Java 启动方式部署探针（我们是 Spring Boot 应用程序，需要使用 java -jar 的方式启动应用）

```
java -javaagent:/agent/skywalking-agent.jar -Dskywalking.agent.service_name=nacos-provider -Dskywalking.collector.backend_service=localhost:11800 -jar yourApp.jar
```

* Docker 启动方式部署探针