package cn.ycm.springbootdemo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-12
 */
@RestController
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
