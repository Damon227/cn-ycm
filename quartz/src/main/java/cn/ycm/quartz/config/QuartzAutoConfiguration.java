package cn.ycm.quartz.config;

import cn.ycm.quartz.controllers.IndexController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-12
 */
@Configuration
@ConditionalOnClass
public class QuartzAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public IndexController indexController() {
        return new IndexController();
    }
}
