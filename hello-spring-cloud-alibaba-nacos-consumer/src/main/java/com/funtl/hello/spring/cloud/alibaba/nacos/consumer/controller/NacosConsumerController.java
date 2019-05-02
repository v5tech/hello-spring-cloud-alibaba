package com.funtl.hello.spring.cloud.alibaba.nacos.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class NacosConsumerController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String appName;

    /**
     * 使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问，该方式使用时切忌在RestTemplate上标记@LoadBalanced注解
     *
     * @return
     */
    @GetMapping(value = "/echo/app/name")
    public String echo() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
        // 获取Nacos元数据
        // RibbonLoadBalancerClient.RibbonServer ribbonServer = (RibbonLoadBalancerClient.RibbonServer) loadBalancerClient.choose("nacos-provider");
        // NacosServer nacosServer= (NacosServer) ribbonServer.getServer();
        // System.out.println(nacosServer.getMetadata());
        String url = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), appName);
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * 使用RestTemplate负载均衡调用服务，RestTemplate需标注@LoadBalanced注解
     *
     * @return
     */
    @GetMapping(value = "/hi")
    public String hi() {
        return restTemplate.getForObject("http://nacos-provider/hi", String.class);
    }
}