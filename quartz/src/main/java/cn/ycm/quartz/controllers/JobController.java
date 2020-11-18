package cn.ycm.quartz.controllers;

import cn.ycm.quartz.entity.QrtzCronTriggers;
import cn.ycm.quartz.service.QrtzCronTriggerService;
import cn.ycm.quartz.service.jobs.DemoJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-17
 */
@Controller
public class JobController {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private QrtzCronTriggerService qrtzCronTriggerService;

    @GetMapping("/createJob")
    public void createJob(@RequestParam String jobName) throws SchedulerException {
        String group = "group1";
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (cronTrigger == null) {
            JobDetail jobDetail = JobBuilder.newJob(DemoJob.class).withIdentity(jobName, group).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, group).withSchedule(cronScheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        }
    }

    @GetMapping("/job/list")
    public String jobList(HttpServletRequest request, @RequestParam String name) {
        request.setAttribute("name", name);
        return "jobList";
    }

    @GetMapping("/trigger/list")
    public String triggerList(HttpServletRequest request){
        List<QrtzCronTriggers> cronTriggers = qrtzCronTriggerService.listTriggers();
        request.setAttribute("cronTriggers", cronTriggers);
        return "triggerList";
    }
}
