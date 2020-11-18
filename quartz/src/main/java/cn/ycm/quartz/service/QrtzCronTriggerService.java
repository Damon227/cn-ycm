package cn.ycm.quartz.service;

import cn.ycm.quartz.entity.QrtzCronTriggers;
import cn.ycm.quartz.repository.QrtzCronTriggersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-18
 */
@Service
public class QrtzCronTriggerService {
    @Autowired
    private QrtzCronTriggersRepository qrtzCronTriggersRepository;

    public List<QrtzCronTriggers> listTriggers() {
        return qrtzCronTriggersRepository.list();
    }
}
