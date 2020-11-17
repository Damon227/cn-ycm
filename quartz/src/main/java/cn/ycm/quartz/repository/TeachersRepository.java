package cn.ycm.quartz.repository;

import cn.ycm.quartz.entity.Teachers;
import cn.ycm.quartz.mapper.TeachersMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-13
 */
@Repository
public class TeachersRepository extends ServiceImpl<TeachersMapper, Teachers> {
}
