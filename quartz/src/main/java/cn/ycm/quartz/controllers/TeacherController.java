package cn.ycm.quartz.controllers;

import cn.ycm.quartz.entity.Teachers;
import cn.ycm.quartz.service.TeachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-13
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeachersService teachersService;

    @GetMapping("/getall")
    public Object getAllTeachers(){
        List<Teachers> teachers = teachersService.listAll();
        return teachers;
    }

    @GetMapping("/getone")
    public Teachers getById(@RequestParam int id){
        return teachersService.getById(id);
    }
}
