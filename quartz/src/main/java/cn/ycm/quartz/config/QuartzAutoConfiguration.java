package cn.ycm.quartz.config;

import cn.ycm.quartz.controllers.JobController;
import cn.ycm.quartz.mapper.QrtzCronTriggersMapper;
import cn.ycm.quartz.repository.QrtzCronTriggersRepository;
import cn.ycm.quartz.service.QrtzCronTriggerService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationImportSelector;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Properties;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-12
 */
@Configuration
public class QuartzAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public JobController jobController() {
        return new JobController();
    }

    @Bean
    @ConditionalOnMissingBean
    public QrtzCronTriggerService qrtzCronTriggerService() {
        return new QrtzCronTriggerService();
    }

    @Bean
    @ConditionalOnMissingBean
    public QrtzCronTriggersRepository qrtzCronTriggersRepository() {
        return new QrtzCronTriggersRepository();
    }

    @Bean
    @ConditionalOnMissingBean
    public JobFactory jobFactory() {
        return new cn.ycm.quartz.config.JobFactory();
    }

    @Bean
    @ConditionalOnMissingBean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(this.jobFactory());
        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        //延长启动
        schedulerFactoryBean.setStartupDelay(1);
        //设置加载的配置文件
        schedulerFactoryBean.setConfigLocation(new ClassPathResource("/quartz.properties"));
        return schedulerFactoryBean;
    }

    @Bean
    @ConditionalOnMissingBean
    public Scheduler scheduler() {
        return this.schedulerFactoryBean().getScheduler();
    }
}
