package cn.ycm.quartz.repository;

import cn.ycm.quartz.entity.QrtzCronTriggers;
import cn.ycm.quartz.mapper.QrtzCronTriggersMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-18
 */
@Repository
public class QrtzCronTriggersRepository extends ServiceImpl<QrtzCronTriggersMapper, QrtzCronTriggers> {

    @Autowired
    private QrtzCronTriggersMapper mapper;

    public QrtzCronTriggers getOne(String scheduler, String triggerName, String triggerGroup) {
        LambdaQueryWrapper<QrtzCronTriggers> query = new QueryWrapper<QrtzCronTriggers>().lambda()
                .eq(QrtzCronTriggers::getSchedName, scheduler)
                .eq(QrtzCronTriggers::getTriggerName, triggerName)
                .eq(QrtzCronTriggers::getTriggerGroup, triggerGroup);
        return mapper.selectOne(query.last("limit 1"));
    }
}
