package com.highpay.zoom.netloan.entity;


import java.util.Date;

/**
 *
 *
 * @author jasonChiu
 * @time 2017/5/24 20:30
 * since 1.0
 */
public class BaseEntity {

	private Long id;

	private Date createTime;

	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
