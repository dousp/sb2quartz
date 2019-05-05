package com.dou.sb2quartz.conf.quartz.conf;

import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 实现自定义quartz配置
 */
@Configuration
@EnableScheduling
public class QuartzConf implements SchedulerFactoryBeanCustomizer {

    @Override
    public void customize(SchedulerFactoryBean schedulerFactoryBean) {
        // schedulerFactoryBean.setStartupDelay(10);
        // schedulerFactoryBean.setAutoStartup(true);
        // schedulerFactoryBean.setOverwriteExistingJobs(true);
    }

}
