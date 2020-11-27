package cn.ycm.quartz.service;

import cn.ycm.quartz.advice.BizException;
import cn.ycm.quartz.entity.QrtzCronTriggers;
import cn.ycm.quartz.entity.QrtzTriggers;
import cn.ycm.quartz.objmapping.QrtzMapping;
import cn.ycm.quartz.pojo.vo.AddJobRequest;
import cn.ycm.quartz.pojo.vo.QrtzTriggerEx;
import cn.ycm.quartz.repository.QrtzCronTriggersRepository;
import cn.ycm.quartz.repository.QrtzTriggersRepository;
import cn.ycm.quartz.service.jobs.DemoJob;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-18
 */
@Service
public class QrtzTriggerService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private QrtzTriggersRepository qrtzTriggersRepository;

    @Autowired
    private QrtzCronTriggersRepository qrtzCronTriggersRepository;

    public List<QrtzTriggers> listTriggers() {
        return qrtzTriggersRepository.list();
    }

    public IPage<QrtzTriggerEx> getPageExTriggers(int pageIndex, int pageSize) {
        IPage<QrtzTriggers> pageData = qrtzTriggersRepository.getPage(pageIndex, pageSize);
        List<QrtzTriggers> triggers = pageData.getRecords();

        List<QrtzTriggerEx> result = new ArrayList<>();
        for (QrtzTriggers trigger : triggers) {
            QrtzTriggerEx resultItem = QrtzMapping.INSTANCE.toQrtzTriggerEx(trigger);
            QrtzCronTriggers cronTrigger = qrtzCronTriggersRepository.getOne(trigger.getSchedName(), trigger.getTriggerName(), trigger.getTriggerGroup());
            if (cronTrigger != null) {
                resultItem.setCronExpression(cronTrigger.getCronExpression());
                resultItem.setTimeZoneId(cronTrigger.getTimeZoneId());
            }
            result.add(resultItem);
        }

        IPage<QrtzTriggerEx> resultPage = new Page<>();
        resultPage.setRecords(result);
        resultPage.setPages(pageData.getPages());
        resultPage.setTotal(pageData.getTotal());
        resultPage.setSize(pageData.getSize());

        return resultPage;
    }

    public boolean addJob(AddJobRequest request) throws SchedulerException, BizException {
        JobDetail jobDetail = JobBuilder.newJob(DemoJob.class).withIdentity(request.getJobName(), request.getJobGroup()).build();
        if (scheduler.checkExists(jobDetail.getKey())) {
            throw new BizException(0, "任务名称重复");
        }

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(request.getCronExpression());
        TriggerKey triggerKey = TriggerKey.triggerKey(request.getTriggerName(), request.getTriggerGroup());
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (cronTrigger == null) {
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(request.getTriggerName(), request.getTriggerGroup()).withSchedule(cronScheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            throw new BizException(0, "触发器名称重复");
        }

        return true;
    }
}
