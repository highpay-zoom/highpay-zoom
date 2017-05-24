package com.highpay.zoom.netloan.dao;

import java.util.List;

/**
 *
 * DAO层
 *
 * @author jasonChiu
 * @time 2017/5/13.
 * since 1.0
 */
public interface BaseDao<T> {
	/**
	 *
	 * 插入
	 *
	 * @author jasonChiu
	 * @time   2017/5/13 15:59
	 * since 1.0
	 */
	int insert(T t);

	/**
	 *
	 * 通过唯一键 获取对象
	 *
	 * @author jasonChiu
	 * @time   2017/5/13 16:00
	 * since 1.0
	 */
	T get(T t);

	/**
	 *
	 * 主键查询
	 * 
	 * @author jasonChiu
	 * @time   2017/5/13 16:00
	 * since 1.0
	 */
	T queryById(T t);

	/**
	 *
	 * 通过主键更新
	 * 
	 * @author jasonChiu
	 * @time   2017/5/13 16:00
	 * since 1.0
	 */
	int updateById(T t);

	/**
	 *
	 * 查询列表
	 * 
	 * @author jasonChiu
	 * @time   2017/5/13 16:00
	 * since 1.0
	 */
	List<T> list(T t);
}
