package cn.ycm.quartz.pojo.vo;

import cn.ycm.quartz.entity.QrtzTriggers;

/**
 * @author YUANCHENGMAN
 * @date 2020-11-18
 */
public class QrtzTriggerEx extends QrtzTriggers {
    private String cronExpression;

    private String timeZoneId;

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }
}
