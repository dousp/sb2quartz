package com.dou.sb2quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value= {"com.dou.sb2quartz.*"})
@SpringBootApplication
public class Sb2quartzApplication {

    private static Logger logger = LoggerFactory.getLogger(Sb2quartzApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(Sb2quartzApplication.class, args);
        logger.info("【【【【【【XinApplication已启动】】】】】】");
    }

}
