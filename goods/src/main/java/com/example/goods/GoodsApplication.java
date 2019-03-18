package com.example.goods;


import com.example.goods.service.IActiveMQService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;


@EnableEurekaClient
@RestController
@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)
public class GoodsApplication {

    private static final Logger LOGGER = LogManager.getLogger(GoodsApplication.class);

    @Autowired
    private IActiveMQService activeMQService;

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(GoodsApplication.class, args);
        int index = 0;

        while(true) {
            Thread.sleep(1000);
            LOGGER.info("value is: " + index++);

        }

    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String home(@RequestParam String name) throws JMSException {

        activeMQService.sendMessage("hi I'm "+ name);
        return "hi " + name + ",i am from port:" + port;
    }

    @RequestMapping("/consume")
    public String consume() throws JMSException {
        LOGGER.info("Hello consume!!!");
        return activeMQService.consumeMessage();
    }

    @RequestMapping("/test")
    @ResponseBody
    public void test() throws JMSException {
       LOGGER.warn("test");
    }

}
