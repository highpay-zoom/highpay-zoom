package com.highpay.zoom.netloan.event;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 事件执行上下文
 *
 * @author jasonChiu
 * @time 2017/4/17.
 * since 1.0
 */
public class EventContext implements Serializable {
    //计划ID
    private Long tmpId;
    //执行事件日期
    private Date eventDate;
    //事件类型
    private EventType eventType;

    public EventContext() {
    }

    public EventContext(Long tmpId, Date eventDate, EventType eventType) {
        this.tmpId = tmpId;
        this.eventDate = eventDate;
        this.eventType = eventType;
    }


    public Long getTmpId() {
        return tmpId;
    }

    public void setTmpId(Long tmpId) {
        this.tmpId = tmpId;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
