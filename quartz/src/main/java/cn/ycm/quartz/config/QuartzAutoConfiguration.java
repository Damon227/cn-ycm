package cn.ycm.quartz.config;

import cn.ycm.quartz.controllers.IndexController;
import cn.ycm.quartz.controllers.JobController;
import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-12
 */
@Configuration
public class QuartzAutoConfiguration {

    @Autowired
    private JobFactory jobFactory;

    @Bean
    @ConditionalOnMissingBean
    public IndexController indexController() {
        return new IndexController();
    }


    @Bean
    @ConditionalOnMissingBean
    public JobController jobController() {
        return new JobController();
    }

    @Bean
    @ConditionalOnMissingBean
    public cn.ycm.quartz.config.JobFactory jobFactory(){
        return new cn.ycm.quartz.config.JobFactory();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(jobFactory);
        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        //延长启动
        schedulerFactoryBean.setStartupDelay(1);
        //设置加载的配置文件
        schedulerFactoryBean.setConfigLocation(new ClassPathResource("/quartz.properties"));
        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler() {
        return this.schedulerFactoryBean().getScheduler();
    }
}
