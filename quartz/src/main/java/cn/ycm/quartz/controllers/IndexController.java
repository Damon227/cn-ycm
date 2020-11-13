package cn.ycm.quartz.controllers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-12
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "hello";
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void sayHello(){
        System.out.println("hello" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
