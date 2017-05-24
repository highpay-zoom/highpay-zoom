package com.highpay.zoom.netloan.event;

/**
 *
 *
 * @author jasonChiu
 * @time   2017/4/17 16:14
 * since 1.0
 */
public enum EventType {
    PAYBACK("F01","拉取回款"),
    ROLL("F02","滚动数据"),
    SHARE_PROFIT("F03","计算分红"),
    TODAY_INTEREST("F04","当日产品起息"),
    SETTLE_AUDIT("F05","阈值审核");

    public String code;
    public String description;

    EventType(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
