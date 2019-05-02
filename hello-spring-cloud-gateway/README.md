# hello-spring-cloud-gateway

依次运行 Nacos 服务、NacosProviderApplication、NacosConsumerApplication、NacosConsumerFeignApplication、GatewayApplication

打开浏览器访问：http://localhost:9000/nacos-consumer/echo/app/name

Hello Nacos Discovery nacos-consumer i am from port 8082

打开浏览器访问：http://localhost:9000/nacos-consumer-feign/echo/hi

Hello Nacos Discovery Hi Feign i am from port 8082

注意：请求方式是 http://路由网关IP:路由网关Port/服务名/**