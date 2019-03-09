package com.example.ribbon.control;

import com.example.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {
    @Autowired
    HelloService helloService;

    //访问ORDER服务器
    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name)
    {
        return helloService.hiService(name);
    }

    //访问GOODS服务器
    @RequestMapping(value = "/hi1")
    public String hi1(@RequestParam String name)
    {
        return helloService.hiService1(name);
    }
}
