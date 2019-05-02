package com.funtl.hello.spring.cloud.alibaba.nacos.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class NacosWebfluxConsumerController {

    @Autowired
    private WebClient.Builder webClient;

    @GetMapping(value = "/hi")
    public Mono<String> hi() {
        return webClient.build()
                .get().uri("http://nacos-provider/hi")
                .retrieve().bodyToMono(String.class);
    }
}