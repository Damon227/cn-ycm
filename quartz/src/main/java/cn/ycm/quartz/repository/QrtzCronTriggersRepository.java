package cn.ycm.quartz.repository;

import cn.ycm.quartz.entity.QrtzCronTriggers;
import cn.ycm.quartz.mapper.QrtzCronTriggersMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-18
 */
@Repository
public class QrtzCronTriggersRepository extends ServiceImpl<QrtzCronTriggersMapper, QrtzCronTriggers> {
}
