package cn.ycm.quartz.controllers;

import cn.ycm.quartz.advice.BizException;
import cn.ycm.quartz.pojo.vo.AddJobRequest;
import cn.ycm.quartz.pojo.vo.QrtzTriggerEx;
import cn.ycm.quartz.service.QrtzCronTriggerService;
import cn.ycm.quartz.service.QrtzTriggerService;
import cn.ycm.quartz.service.jobs.DemoJob;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private QrtzTriggerService qrtzTriggerService;

    @GetMapping("/quartz")
    public String quartz() {
        return "index";
    }

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
    public String triggerList(HttpServletRequest request) {
        IPage<QrtzTriggerEx> triggerExPage = qrtzTriggerService.getPageExTriggers(0, 10);
        request.setAttribute("triggerExPage", triggerExPage);
        return "triggerList";
    }

    @GetMapping("/job/add")
    public String addJob(HttpServletRequest request) {
        return "addJob";
    }

    @PostMapping("/api/job/add")
    @ResponseBody
    public boolean addJob(@Validated AddJobRequest request) throws SchedulerException, BizException {
        return qrtzTriggerService.addJob(request);
    }

    @GetMapping("/demo")
    public String demo(HttpServletRequest request){
        return "demo";
    }
}
