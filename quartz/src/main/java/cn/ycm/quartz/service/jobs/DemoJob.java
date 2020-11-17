package cn.ycm.quartz.service.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-17
 */
@DisallowConcurrentExecution
public class DemoJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("demo job fired at " + LocalDateTime.now());
    }
}
