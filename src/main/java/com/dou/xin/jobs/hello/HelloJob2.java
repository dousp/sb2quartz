package com.dou.xin.jobs.hello;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class HelloJob2 extends QuartzJobBean {

    private static Logger logger = LoggerFactory.getLogger(HelloJob2.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取任务详情内的数据集合
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        //获取商品编号
        // Long goodId = dataMap.getLong("goodId");
        logger.info("HelloJob2");
        logger.info(dataMap.toString());

    }
}