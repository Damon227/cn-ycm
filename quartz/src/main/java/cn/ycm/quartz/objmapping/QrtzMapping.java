package cn.ycm.quartz.objmapping;

import cn.ycm.quartz.entity.QrtzTriggers;
import cn.ycm.quartz.pojo.vo.QrtzTriggerEx;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-18
 */
@Mapper
public interface QrtzMapping {

    QrtzMapping INSTANCE = Mappers.getMapper(QrtzMapping.class);

    QrtzTriggerEx toQrtzTriggerEx(QrtzTriggers trigger);
}
