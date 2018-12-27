package com.bootdemo.system.common.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志实体
 * 
 * @author suzhicheng
 *
 */

@Data
@TableName("t_sys_log")
public class LogEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2935518572107665808L;
	/**
	 * ID
	 */
	@TableId(type=IdType.UUID)
	private String id;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 操作类型
	 */
	private String operation;
	/**
	 * 请求时间
	 */
	private Integer time;
	/**
	 * 方法
	 */
	private String method;
	/**
	 * 参数
	 */
	private String params;
	/**
	 * ip
	 */
	private String ip;
	/**
	 * 创建时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
//	@TableField(value="createDate")
	private Date createDate;

}