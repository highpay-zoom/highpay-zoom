package com.highpay.zoom.netloan.exception;

import com.highpay.zoom.netloan.event.EventContext;

/**
 *
 * 资金动态管理异常处理接口服务
 *
 * @author jasonChiu
 * @time 2017/4/17.
 * since 1.0
 */
public interface HandleExceptionService {

    void handleException(EventContext context);

}
