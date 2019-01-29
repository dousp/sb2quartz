package com.dou.xin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value= {"com.dou.xin.*"})
@SpringBootApplication
public class XinApplication {

    private static Logger logger = LoggerFactory.getLogger(XinApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(XinApplication.class, args);
        logger.info("【【【【【【XinApplication已启动】】】】】】");
    }

}
