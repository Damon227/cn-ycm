package cn.ycm.quartz.service;

import cn.ycm.quartz.entity.Teachers;
import cn.ycm.quartz.repository.TeachersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-13
 */
@Service
public class TeachersService {

    @Autowired
    private TeachersRepository teachersRepository;
    
    public List<Teachers> listAll(){
        List<Teachers> list = teachersRepository.list();
        return list;
    }

    public Teachers getById(int id){
        Teachers teacher = teachersRepository.getById(id);
        return teacher;
    }
}
