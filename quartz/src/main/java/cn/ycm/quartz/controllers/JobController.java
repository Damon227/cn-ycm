package cn.ycm.quartz.controllers;

import cn.ycm.quartz.service.jobs.DemoJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-17
 */
@Controller
public class JobController {

    @Autowired
    private Scheduler scheduler;

    @GetMapping("/createJob")
    public void createJob() throws SchedulerException {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        TriggerKey triggerKey = TriggerKey.triggerKey("ycm", "123");
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (cronTrigger == null) {
            JobDetail jobDetail = JobBuilder.newJob(DemoJob.class).withIdentity("ycm", "123").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("ycm", "123").withSchedule(cronScheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        }
    }

    @GetMapping("/jobList")
    public String jobList(HttpServletRequest request, @RequestParam String name) {
        request.setAttribute("name", name);
        return "jobList";
    }
}
