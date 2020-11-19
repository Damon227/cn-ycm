package cn.ycm.quartz.repository;

import cn.ycm.quartz.entity.QrtzTriggers;
import cn.ycm.quartz.mapper.QrtzTriggersMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-18
 */
@Repository
public class QrtzTriggersRepository extends ServiceImpl<QrtzTriggersMapper, QrtzTriggers> {

    @Autowired
    private QrtzTriggersMapper mapper;

    public IPage<QrtzTriggers> getPage(int pageIndex, int pageSize) {
        LambdaQueryWrapper<QrtzTriggers> query = new LambdaQueryWrapper<>();
        IPage<QrtzTriggers> page = new Page<>(pageIndex, pageSize);
        return mapper.selectPage(page, query);
    }
}
