package com.example.goods;

import com.example.goods.service.IActiveMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;


@EnableEurekaClient
@RestController
@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class GoodsApplication {

    @Autowired
    private IActiveMQService activeMQService;

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String home(@RequestParam String name) throws JMSException {
        activeMQService.sendMessage();
        return "hi " + name + ",i am from port:" + port;
    }

    @RequestMapping("/consume")
    public void consume() throws JMSException {
        activeMQService.consumeMessage();
    }

}
