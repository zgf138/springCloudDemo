package com.example.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "errHystrix")
    public String hiService(String name)
    {
        return restTemplate.getForObject("http://SERVICE-ORDER/hi?name=" + name, String.class);
    }

    @HystrixCommand(fallbackMethod = "errHystrix")
    public String hiService1(String name)
    {
        return restTemplate.getForObject("http://SERVICE-GOODS/hi?name=" + name, String.class);
    }

    public String errHystrix(String name){
        return "hey " + name + ",there is some problem with hi page";
    }}
