package com.dou.sb2quartz.conf.quartz.web.controller;

import com.dou.sb2quartz.conf.quartz.BaseJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value="/job")
public class JobController {

    private static Logger log = LoggerFactory.getLogger(JobController.class);

    @Autowired
    @Qualifier("quartzJdbcTemplate")
    private JdbcTemplate template;

    /**
     * 加入Qulifier注解，通过名称注入bean
      */
    @Autowired
    // @Qualifier("scheduler")
    private Scheduler scheduler;

    private static BaseJob getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob)class1.newInstance();
    }

    private static Job getJobClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (Job)class1.newInstance();
    }

    @PostMapping(value="/addjob")
    public void addjob(@RequestParam(value="jobClassName")String jobClassName,
                       @RequestParam(value="jobGroupName")String jobGroupName,
                       @RequestParam(value="cronExpression")String cronExpression) throws Exception {
        addJob(jobClassName, jobGroupName, cronExpression);
    }

    private void addJob(String jobClassName, String jobGroupName, String cronExpression)throws Exception{

        // 启动调度器
        scheduler.start();

        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(getJobClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();
        // JobDetail jobDetail = JobBuilder.newJob( Class.forName(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();

        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
                .withSchedule(scheduleBuilder).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败"+e);
            throw new Exception("创建定时任务失败");
        }
    }


    @PostMapping(value="/pausejob")
    public void pausejob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName) throws Exception {
        jobPause(jobClassName, jobGroupName);
    }

    private void jobPause(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    @PostMapping(value="/resumejob")
    public void resumejob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName) throws Exception {
        jobresume(jobClassName, jobGroupName);
    }

    private void jobresume(String jobClassName, String jobGroupName) throws Exception {
        scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    @PostMapping(value="/reschedulejob")
    public void rescheduleJob(@RequestParam(value="jobClassName")String jobClassName,
                              @RequestParam(value="jobGroupName")String jobGroupName,
                              @RequestParam(value="cronExpression")String cronExpression) throws Exception {
        jobreschedule(jobClassName, jobGroupName, cronExpression);
    }

    private void jobreschedule(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            System.out.println("更新定时任务失败"+e);
            throw new Exception("更新定时任务失败");
        }
    }


    @PostMapping(value="/deletejob")
    public void deletejob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName) throws Exception {
        jobdelete(jobClassName, jobGroupName);
    }

    private void jobdelete(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    @GetMapping(value="/queryjob")
    public Map<String, Object> queryjob(@RequestParam(value="pageNum")Integer pageNum, @RequestParam(value="pageSize")Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        // PageInfo<JobAndTrigger> jobAndTrigger = iJobAndTriggerService.getJobAndTriggerDetails(pageNum, pageSize);
        // map.put("JobAndTrigger", jobAndTrigger);
        // map.put("number", jobAndTrigger.getTotal());

        StringBuilder sb = new StringBuilder(256);
        sb.append(" SELECT t.*  FROM ( ");
        sb.append(" SELECT ");
        sb.append("         qrtz_job_details.JOB_NAME, ");
        sb.append("         qrtz_job_details.JOB_GROUP, ");
        sb.append("         qrtz_job_details.JOB_CLASS_NAME, ");
        sb.append("         qrtz_triggers.TRIGGER_NAME, ");
        sb.append("         qrtz_triggers.TRIGGER_GROUP, ");
        sb.append("         qrtz_cron_triggers.CRON_EXPRESSION, ");
        sb.append("         qrtz_cron_triggers.TIME_ZONE_ID ");
        sb.append(" FROM ");
        sb.append("         qrtz_job_details as qrtz_job_details ");
        sb.append(" JOIN qrtz_triggers as qrtz_triggers ");
        sb.append(" JOIN qrtz_cron_triggers as qrtz_cron_triggers ON qrtz_job_details.JOB_NAME = qrtz_triggers.JOB_NAME ");
        sb.append(" AND qrtz_triggers.TRIGGER_NAME = qrtz_cron_triggers.TRIGGER_NAME ");
        sb.append(" AND qrtz_triggers.TRIGGER_GROUP = qrtz_cron_triggers.TRIGGER_GROUP ");
        sb.append(" ) t ");

        List<Map<String,Object>> list = template.queryForList(sb.toString());
        map.put("list",  list);
        map.put("count",  list.size());

        return map;
    }




}
